package kr.or.cnu.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.cnu.service.BoardService;
import kr.or.cnu.vo.AtchFileVO;
import kr.or.cnu.vo.BoardVO;
import kr.or.cnu.vo.PagingVO;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;

	// ���������� �����ߴ��κ�
//	@GetMapping("/board.do")
//	public String boardMain(@ModelAttribute BoardVO boardVo) {
//		List<BoardVO> boardList = bs.boardList(); 
//		System.out.println(boardList);
//		return "board/board";
//	}
	// �������

	@GetMapping("/board.do")
	public String boardMain(@ModelAttribute BoardVO boardVo, Model model, @ModelAttribute PagingVO pagingVO,
			@RequestParam(defaultValue = "1") int nowPage) { // ���� �Ķ���Ϳ� �𵨼���

		int total = boardService.total(pagingVO);

		String search = pagingVO.getSearch();
		String searchKeyword = pagingVO.getSearchKeyword();

		pagingVO = new PagingVO(total, nowPage, 5);

		pagingVO.setSearch(search);
		pagingVO.setSearchKeyword(searchKeyword);

		List<BoardVO> boardList = boardService.boardList(pagingVO);

		model.addAttribute("boardList", boardList); // ���� ����Ʈ��� �̸����� boardList ����Ʈ�� ������Ʈ�� �����.
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("search", search);
		model.addAttribute("searchKeyword", searchKeyword);
		System.out.println("���׽�Ʈ");
		return "board/board";
	}

//	@GetMapping("/boardDetail.do") //���� �����������  ������ö� url ������ boardDetail.do?boardNo=�۹�ȣ �̷������� ������ �Ķ���ͷ� �۹�ȣ�� �޾��ٰ���
//	public String boardDetail(@RequestParam String boardNo, Model model) {  
//							//	�������� requestParam������̼��� �Ἥ request�� �ѱ�(url�� �ִ� boardNo) �̸��� ���� �����ϸ� �ڵ����θ��ε�
//		//�״��� �߹޾������� ����Ʈ���ѹ� Ȯ���غ��� (Ȯ���Ҷ� ���� �� ������ϰ�)
//		 System.out.println("�Ѿ�� �۹�ȣ : " + boardNo);
//		//�߳Ѿ�´� �׷� ���� �� �۹�ȣ�� �Խñ� ������ ��񿡼� �����;���
//		//������ ��Ʈ�ѷ�(�Ķ���ͷ� BoardNo) >> ����(�Ķ���ͷ� BoardNo) >> �ٿ�(�Ķ���ͷ� BoardNo) >> �������� 
//		BoardVO boardVo = boardService.boardDetail(boardNo); // �ۼ��� �������ָ��
//		//�̾ƿ����� �����
//		System.out.println(boardVo); //�߳Ѿ��
//		
//		//�Ѿ�°� Ȯ�������� ���� model�� �� �Ѱ��ش�.
//		model.addAttribute("boardVo", boardVo);
//		//�Ѱ������� ���� ȭ������
//		
//		return "board/boardDetail";
//	}

	// ���ø�����
	@GetMapping("/{boardNo}/boardDetail.do")
	public String boardDetail(@PathVariable String boardNo, Model model) {

		BoardVO boardVo = boardService.boardDetail(boardNo); // �ۼ��� �������ָ��
		model.addAttribute("boardVo", boardVo);
		List<Map<String, Object>> commentList = boardService.commentList(boardNo);
		model.addAttribute("commentList", commentList);
		
		return "board/boardDetail";

	}

	// ajax�� ����ϴ� �޼����
	@ResponseBody // ���������
	@PostMapping("/commentInsert.do")
	public String commentInsert(@RequestParam Map<String, Object> map) {

		int cnt = boardService.commentInsert(map);

		if (cnt > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@GetMapping("/boardInsert.do")
	public String boardInsert() {

		return "board/boardInsert";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsertPost(MultipartFile []uploadFile) {
		
			//��� �����
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
			String nowFolder = format.format(now);
			String path = "D:\\" + nowFolder;
			
			File file = new File(path); //���丮(����)
			
			
			if(!file.exists()) {
				file.mkdirs();
			}
		
			for(MultipartFile upload : uploadFile) {
				UUID uuid = UUID.randomUUID();
				String atchFileNm = uuid + "-" + upload.getOriginalFilename();
				
				//�����ͺ��̽��� ���� ���Ͽ����� ������ (+���, Ȯ����, �����̸�, ����)
				System.out.println(upload.getOriginalFilename()); //�����̸�
				System.out.println(upload.getSize());  //���Ͽ뷮
				
				File saveFile = new File(path, atchFileNm);
				
				try {
					upload.transferTo(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			}
			
			
		return "";
	}
	
	
//	@PostMapping("/boardInsert.do")
//	public String boardInsertPost(MultipartFile[]uploadFile) {
//		
//		String atchFileCd = fileUpload(uploadFile);
//		System.out.println("���Ϲ�ȣ : " + atchFileCd);
//		
//		return "";
//	}
	
	
	public String fileUpload(MultipartFile[]uploadFile) {
		
		String atchFileNo = boardService.atchFileNo();
		
		int forIndex = 0;
		for(MultipartFile upload : uploadFile) {
			AtchFileVO fileVo = new AtchFileVO();
			
			UUID uuid = UUID.randomUUID();
			int sn = forIndex;	//����
			String orignAtchFileNm = upload.getOriginalFilename(); //���������̸�
			String atchFileNm = uuid + "-" + upload.getOriginalFilename(); //���������̸�
			long atchFileSize = upload.getSize();	//���ϻ�����
			//Ȯ����
			int index = upload.getOriginalFilename().split("\\.").length;
			String extsn = upload.getOriginalFilename().split("\\.")[index-1]; //Ȯ����
			
			fileVo.setAtchFileNo(atchFileNo);
			fileVo.setSn(forIndex);
			fileVo.setAtchFileNm(atchFileNm);
			fileVo.setOrignAtchFileNm(orignAtchFileNm);
			fileVo.setAtchFileSize(atchFileSize);
			fileVo.setExtsn(extsn);
			fileVo.setAtchFile("");
			
			File file = new File(getFolder(), upload.getOriginalFilename());
			
			boardService.atchFile(fileVo);
			
			try {
				upload.transferTo(file);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			forIndex++;
		}
		
		return atchFileNo;
	}
	
	
	public String getFolder() {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
		String nowFolder = format.format(now);
		String path = "D:\\" + nowFolder;
		
		File file = new File(path);
		
		if(!file.exists()) {
			try{
				file.mkdirs();
			}catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		return path;
	}
	
	@ResponseBody
	@PostMapping("/commentDelete.do")
	public String commentDelete(@RequestParam Map<String, Object> map) {
		
		int cnt = boardService.commentDelete(map);
		if(cnt > 0) {
			return "ok";
		}else {
			return "fail";
		}
	}
	
}
