package kr.or.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.board.service.BoardService;
import kr.or.board.vo.AtchFileVO;
import kr.or.board.vo.BoardVO;
import kr.or.board.vo.PagingVO;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	/**
	 * 게시판 메인
	 * @param model
	 * @param pagingVO 페이징, 검색정보
	 * @param nowPage 현재페이지 (기본값 1)
	 * @return 메인화면
	 */
	@GetMapping("/main.do")
	public String boardMain(Model model
							, @ModelAttribute PagingVO pagingVO
							, @RequestParam (defaultValue = "1") String nowPage) {
		
		int boardTotal = boardService.getTotal(pagingVO);
		
		pagingVO = new PagingVO(boardTotal, Integer.parseInt(nowPage), 10, pagingVO.getSearchFlag(), pagingVO.getSearchKeyword());
		
		List<BoardVO>boardList = boardService.boardList(pagingVO);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardMain";
	}
	
	@GetMapping("/boardInsert.do")
	public String boardInsert() {
		
		return "board/boardInsert";
	}
	
	/**
	 * 게시글입력
	 * @param atchFile 첨부파일들
	 * @param boardVO 입력정보
	 * @return 메인화면으로 redirect
	 */
	@PostMapping("/boardInsert.do")
	public String boardInsertPost(MultipartFile[]atchFile
								 ,@ModelAttribute BoardVO boardVO) {
		
		String atchFileNo = fileUpload(atchFile);
		boardVO.setAtchFileNo(atchFileNo);
		
		boardService.boardInsert(boardVO);
		
		return "redirect:/main.do";
	}
	
	@ResponseBody
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(@ModelAttribute BoardVO boardVo) {
		
		boardService.boardUpdate(boardVo);
		
		return "";
	}
	
	@GetMapping("/detail/{boardNo}")
	public String boardDetail(@PathVariable String boardNo, Model model) {
		
		boardService.hitPlus(boardNo);
		
		BoardVO boardVO = boardService.boardDetail(boardNo);
		
		String atchFileNo = boardVO.getAtchFileNo();
		List<Map<String, Object>> commentList = boardService.commentList(boardNo);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("commentList", commentList);
		
		if(atchFileNo != null) {
			List<AtchFileVO> fileList = boardService.fileDetail(atchFileNo);		
			model.addAttribute("fileList", fileList);
		}
		
		return "board/boardDetail";
	}
	
	@ResponseBody
	@PostMapping("/commentInsert.do")
	public Object commentInsert(@RequestParam Map<String, Object>map) {
		System.out.println(map);
		
		boardService.commentInsert(map);
		String commentNo = (String) map.get("commentNo");
		logger.warn("댓글번호 : " + commentNo);
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String regdate = format.format(now);
		map.put("regdate", regdate);
		
		return map;
	}
	
	@ResponseBody
	@PostMapping("/cmtDelete.do")
	public String cmtDelete(@RequestParam String cmtNo) {
		int cnt = boardService.cmtDelete(cmtNo);
		if(cnt > 0) {
			return "ok";
		}else {
			return "fail";
		}
	}
	
	@GetMapping("/boardDelete.do")
	public String boardDel(@RequestParam String boardNo) {
		
		int cnt = boardService.boardDelete(boardNo);

		return "redirect:/main.do";
	}
	
	@GetMapping("/fileDownload.do")
	public void fileDownload (@ModelAttribute AtchFileVO reqFileVO, HttpServletResponse resp) throws IOException {
		
		AtchFileVO fileVO = boardService.filedown(reqFileVO);
		
		System.out.println(fileVO);
		
		File file = new File(fileVO.getFileCours(), fileVO.getAtchFileNm());
		
		byte [] bytes = FileUtils.readFileToByteArray(file);
		
		resp.setContentType("application/octer-stream");
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileVO.getOrignAtchFileNm() + "\";");
		
//		try {
//			
//		OutputStream os = resp.getOutputStream();
//		FileInputStream fis = new FileInputStream(file);
//		int cnt = 0;
//		byte[] bytess = new byte[1024];
//		while((cnt = fis.read(bytess)) != -1) {
//			os.write(bytess, 0, cnt);
//		}
//		fis.close();
//		os.close();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		resp.getOutputStream().write(bytes);
		
		
	}
	
	/**
	 * 파일 저장 및 디비저장
	 * @param atchFile
	 * @return 파일번호
	 */
	public String fileUpload( MultipartFile[]atchFile ) {
		String atchFileNo = boardService.getAtchFileNo();
		int sn = 0;
		for(MultipartFile file : atchFile) {
			//확장자
			int index = file.getOriginalFilename().split("\\.").length;
			String extsn = file.getOriginalFilename().split("\\.")[index-1];
			
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileNo(atchFileNo);
			fileVO.setSn(sn);
			fileVO.setAtchFileNm(atchFileNo+sn+"-"+file.getOriginalFilename());
			fileVO.setOrignAtchFileNm(file.getOriginalFilename());
			fileVO.setAtchFileSize((int)file.getSize());
			fileVO.setExtsn(extsn);
			fileVO.setAtchFile("");
			fileVO.setFileCours(getFolder("update"));
			
			boardService.atchFileUpload(fileVO);
			
			File newFile = new File(getFolder("insert"), atchFileNo+sn+"-"+file.getOriginalFilename());
			
			try {
				file.transferTo(newFile);
				logger.debug("파일저장완료");
			} catch (Exception e) {
				logger.warn("파일업로드 실패 : " + e.getMessage());
			} 
			sn++;
		}
		return atchFileNo;
	}
	
	/**
	 * 저장폴더생성
	 * @return 저장경로
	 */
	public String getFolder(String value) {
		Date date = new Date();
		SimpleDateFormat format; 
		if(value.equals("insert")) {
			format = new SimpleDateFormat("\\yyyy\\MM\\dd\\");
		}else {
			format = new SimpleDateFormat("\\yyyy\\\\MM\\\\dd\\\\");
		}
		String path = "D:\\" + format.format(date);
		
		File file = new File(path);
		
		if(!file.exists()) {
			try {
				file.mkdirs();
				logger.debug("폴더생성");
			}catch (Exception e) {
				logger.error("폴더생성 에러 : " + e.getMessage());
			}
		}
				
		return path;
		
	}
	
	
	
}







