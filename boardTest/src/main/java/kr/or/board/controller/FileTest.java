package kr.or.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.board.service.FileService;

@Controller
public class FileTest {

	@Autowired
	FileService fileService;
	
	@GetMapping("/filetest.do")
	public String fileTest(Model model) {
		
		List<Map<String, Object>> fileList = fileService.getList();
		model.addAttribute("fileList", fileList);
		
		return "filetest/fileTest";
	}
	
	@PostMapping("/filetest.do")
	public String filePost(MultipartFile upload) throws IOException, SerialException, SQLException {
		
		String atchFileNm = upload.getOriginalFilename();
		
		byte[] bytes = upload.getBytes();
		
		Blob blob = new SerialBlob(bytes);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("atchFileNm", atchFileNm);
		map.put("atchFile", blob);
		
		fileService.insertFile(map);
		
		return "redirect:/filetest.do";
	}
	
	@GetMapping("/downTest.do")
	public void fileDown(HttpServletResponse resp) throws IOException {
		List<Map<String, Object>> fileList = fileService.getList();
		Map<String, Object> fileMap = fileList.get(0);
		
		String fileBinary = (String) fileMap.get("ATCH_FILE");
		byte[] bytes = fileBinary.getBytes();
		
		resp.setContentType("application/octer-stream");
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileMap.get("ATCH_FILE_NM") + "\";");
		resp.getOutputStream().write(bytes);
	}
	
}
