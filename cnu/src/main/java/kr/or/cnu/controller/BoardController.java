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

	// 엊그제까지 같이했던부분
//	@GetMapping("/board.do")
//	public String boardMain(@ModelAttribute BoardVO boardVo) {
//		List<BoardVO> boardList = bs.boardList(); 
//		System.out.println(boardList);
//		return "board/board";
//	}
	// 여기까지

	@GetMapping("/board.do")
	public String boardMain(@ModelAttribute BoardVO boardVo, Model model, @ModelAttribute PagingVO pagingVO,
			@RequestParam(defaultValue = "1") int nowPage) { // 여기 파라미터에 모델선언

		int total = boardService.total(pagingVO);

		String search = pagingVO.getSearch();
		String searchKeyword = pagingVO.getSearchKeyword();

		pagingVO = new PagingVO(total, nowPage, 5);

		pagingVO.setSearch(search);
		pagingVO.setSearchKeyword(searchKeyword);

		List<BoardVO> boardList = boardService.boardList(pagingVO);

		model.addAttribute("boardList", boardList); // 보드 리스트라는 이름으로 boardList 리스트를 리퀘스트에 담아줌.
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("search", search);
		model.addAttribute("searchKeyword", searchKeyword);
		System.out.println("깃테스트");
		return "board/board";
	}

//	@GetMapping("/boardDetail.do") //이제 여기까지했음  여기들어올때 url 설정을 boardDetail.do?boardNo=글번호 이런식으로 해줬음 파라미터로 글번호를 받아줄거임
//	public String boardDetail(@RequestParam String boardNo, Model model) {  
//							//	받을때는 requestParam어노테이션을 써서 request로 넘긴(url에 있는 boardNo) 이름을 같게 선언하면 자동으로매핑됨
//		//그다음 잘받아지는지 프린트로한번 확인해볼것 (확인할때 서버 꼭 재실행하고)
//		 System.out.println("넘어온 글번호 : " + boardNo);
//		//잘넘어온다 그럼 이제 이 글번호의 게시글 내용을 디비에서 꺼내와야함
//		//순서는 컨트롤러(파라미터로 BoardNo) >> 서비스(파라미터로 BoardNo) >> 다오(파라미터로 BoardNo) >> 쿼리실행 
//		BoardVO boardVo = boardService.boardDetail(boardNo); // 작성후 생성해주면됨
//		//뽑아왔으면 띄워봐
//		System.out.println(boardVo); //잘넘어옴
//		
//		//넘어온거 확인했으면 이제 model로 또 넘겨준다.
//		model.addAttribute("boardVo", boardVo);
//		//넘겨줬으면 이제 화면으로
//		
//		return "board/boardDetail";
//	}

	// 템플릿변수
	@GetMapping("/{boardNo}/boardDetail.do")
	public String boardDetail(@PathVariable String boardNo, Model model) {

		BoardVO boardVo = boardService.boardDetail(boardNo); // 작성후 생성해주면됨
		model.addAttribute("boardVo", boardVo);
		List<Map<String, Object>> commentList = boardService.commentList(boardNo);
		model.addAttribute("commentList", commentList);
		
		return "board/boardDetail";

	}

	// ajax를 사용하는 메서드는
	@ResponseBody // 붙혀줘야함
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
		
			//경로 만들기
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
			String nowFolder = format.format(now);
			String path = "D:\\" + nowFolder;
			
			File file = new File(path); //디렉토리(폴더)
			
			
			if(!file.exists()) {
				file.mkdirs();
			}
		
			for(MultipartFile upload : uploadFile) {
				UUID uuid = UUID.randomUUID();
				String atchFileNm = uuid + "-" + upload.getOriginalFilename();
				
				//데이터베이스에 넣을 파일에대한 정보들 (+경로, 확장자, 저장이름, 순번)
				System.out.println(upload.getOriginalFilename()); //파일이름
				System.out.println(upload.getSize());  //파일용량
				
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
//		System.out.println("파일번호 : " + atchFileCd);
//		
//		return "";
//	}
	
	
	public String fileUpload(MultipartFile[]uploadFile) {
		
		String atchFileNo = boardService.atchFileNo();
		
		int forIndex = 0;
		for(MultipartFile upload : uploadFile) {
			AtchFileVO fileVo = new AtchFileVO();
			
			UUID uuid = UUID.randomUUID();
			int sn = forIndex;	//순번
			String orignAtchFileNm = upload.getOriginalFilename(); //원본파일이름
			String atchFileNm = uuid + "-" + upload.getOriginalFilename(); //저장파일이름
			long atchFileSize = upload.getSize();	//파일사이즈
			//확장자
			int index = upload.getOriginalFilename().split("\\.").length;
			String extsn = upload.getOriginalFilename().split("\\.")[index-1]; //확장자
			
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
