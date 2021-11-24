package kr.or.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	 * �Խ��� ����
	 * @param model
	 * @param pagingVO ����¡, �˻�����
	 * @param nowPage ���������� (�⺻�� 1)
	 * @return ����ȭ��
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
	 * �Խñ��Է�
	 * @param atchFile ÷�����ϵ�
	 * @param boardVO �Է�����
	 * @return ����ȭ������ redirect
	 */
	@PostMapping("/boardInsert.do")
	public String boardInsertPost(MultipartFile[]atchFile
								 ,@ModelAttribute BoardVO boardVO) {
		
		String atchFileNo = fileUpload(atchFile);
		boardVO.setAtchFileNo(atchFileNo);
		
		boardService.boardInsert(boardVO);
		
		return "redirect:/main.do";
	}
	
	@GetMapping("/detail/{boardNo}")
	public String boardDetail(@PathVariable String boardNo, Model model) {
		
		BoardVO boardVO = boardService.boardDetail(boardNo);
		
		String atchFileNo = boardVO.getAtchFileNo();
		List<AtchFileVO> fileList = boardService.fileDetail(atchFileNo);		
		List<Map<String, Object>> commentList = boardService.commentList(boardNo);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("fileList", fileList);
		model.addAttribute("commentList", commentList);
		
		return "board/boardDetail";
	}
	
	@ResponseBody
	@PostMapping("/commentInsert.do")
	public Object commentInsert(@RequestParam Map<String, Object>map) {
		System.out.println(map);
		
		boardService.commentInsert(map);
		String commentNo = (String) map.get("commentNo");
		logger.warn("��۹�ȣ : " + commentNo);
		
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
	
	/**
	 * ���� ���� �� �������
	 * @param atchFile
	 * @return ���Ϲ�ȣ
	 */
	public String fileUpload( MultipartFile[]atchFile ) {
		String atchFileNo = boardService.getAtchFileNo();
		int sn = 0;
		for(MultipartFile file : atchFile) {
			//Ȯ����
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
			
			boardService.atchFileUpload(fileVO);
			
			File newFile = new File(getFolder(), atchFileNo+sn+"-"+file.getOriginalFilename());
			
			try {
				file.transferTo(newFile);
				logger.debug("��������Ϸ�");
			} catch (Exception e) {
				logger.warn("���Ͼ��ε� ���� : " + e.getMessage());
			} 
			sn++;
		}
		return atchFileNo;
	}
	
	/**
	 * ������������
	 * @return ������
	 */
	public String getFolder() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("\\yyyy\\MM\\dd");
		String path = "D:\\" + format.format(date);
		
		File file = new File(path);
		
		if(!file.exists()) {
			try {
				file.mkdirs();
				logger.debug("��������");
			}catch (Exception e) {
				logger.error("�������� ���� : " + e.getMessage());
			}
		}
				
		return path;
		
	}
	
	
	
}







