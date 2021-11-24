package kr.or.board.vo;

import java.util.Date;

import lombok.Data;
@Data
public class BoardVO {

	private String boardNo;
	private String boardTitle;
	private Date regdate;
	private int hit;
	private String content;
	private String writer;
	private String atchFileNo;
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", regdate=" + regdate + ", hit=" + hit
				+ ", content=" + content + ", writer=" + writer + ", atchFileNo=" + atchFileNo + "]";
	}
	
}
