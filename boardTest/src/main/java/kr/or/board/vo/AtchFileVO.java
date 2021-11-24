package kr.or.board.vo;

import lombok.Data;

public class AtchFileVO {
	private String atchFileNo;
	private int sn;
	private String atchFileNm;
	private String orignAtchFileNm;
	private int atchFileSize;
	private String extsn;
	private String atchFile;
	
	
	
	public String getAtchFileNo() {
		return atchFileNo;
	}



	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}



	public int getSn() {
		return sn;
	}



	public void setSn(int sn) {
		this.sn = sn;
	}



	public String getAtchFileNm() {
		return atchFileNm;
	}



	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}



	public String getOrignAtchFileNm() {
		return orignAtchFileNm;
	}



	public void setOrignAtchFileNm(String orignAtchFileNm) {
		this.orignAtchFileNm = orignAtchFileNm;
	}



	public int getAtchFileSize() {
		return atchFileSize;
	}



	public void setAtchFileSize(int atchFileSize) {
		this.atchFileSize = atchFileSize;
	}



	public String getExtsn() {
		return extsn;
	}



	public void setExtsn(String extsn) {
		this.extsn = extsn;
	}



	public String getAtchFile() {
		return atchFile;
	}



	public void setAtchFile(String atchFile) {
		this.atchFile = atchFile;
	}



	@Override
	public String toString() {
		return "AtchFileVO [atchFileNo=" + atchFileNo + ", sn=" + sn + ", atchFileNm=" + atchFileNm
				+ ", orignAtchFileNm=" + orignAtchFileNm + ", atchFileSize=" + atchFileSize + ", extsn=" + extsn
				+ ", atchFile=" + atchFile + "]";
	}
}
