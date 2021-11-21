package kr.or.cnu.vo;

import java.util.Date;

import lombok.Data;


public class BoardVO {

	private String board_No;
    private String board_Title;
    private Date regdate;
    private int hit;
    private String content;
    private String writer;
    
	


	public String getBoard_No() {
		return board_No;
	}



	public void setBoard_No(String board_No) {
		this.board_No = board_No;
	}



	public String getBoard_Title() {
		return board_Title;
	}



	public void setBoard_Title(String board_Title) {
		this.board_Title = board_Title;
	}



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}



	public int getHit() {
		return hit;
	}



	public void setHit(int hit) {
		this.hit = hit;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}



	@Override
	public String toString() {
		return "BoardVO [board_No=" + board_No + ", board_Title=" + board_Title + ", regdate=" + regdate + ", hit="
				+ hit + ", content=" + content + ", writer=" + writer + "]";
	}



	
}
