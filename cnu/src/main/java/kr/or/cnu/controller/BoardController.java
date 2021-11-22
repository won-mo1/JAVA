package kr.or.cnu.controller;

import java.io.IOException;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.cnu.service.BoardService;
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
	public String boardInsertPost(MultipartFile[]uploadFile, MultipartHttpServletRequest req) {
		System.out.println("���Ⱑ���;���");
		System.out.println("���� : " + uploadFile.length);
		System.out.println(req.getFileNames());
		Iterator<String> itr =  req.getFileNames();
		
		while(itr.hasNext()) {
			 MultipartFile mpf = req.getFile(itr.next());
	         String originFileName = mpf.getOriginalFilename();
	         System.out.println("FILE_INFO: "+originFileName);
		}
		
		for(MultipartFile upload : uploadFile) {
			System.out.println("�����̸� : " + upload.getName());
			System.out.println("���������̸� : " + upload.getOriginalFilename());
			System.out.println("���ϻ����� : " + upload.getSize());
		}
		
		return "";
	}
	
	

}
