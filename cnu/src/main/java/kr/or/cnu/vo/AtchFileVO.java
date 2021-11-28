package kr.or.cnu.vo;

import lombok.Data;

@Data
public class AtchFileVO {

	private String atchFileNo;
	private int sn;
	private String atchFileNm;
	private String orignAtchFileNm;
	private String atchFileSize;
	private String extsn;
	private String atchFile;
	private String fileCours;
	
	@Override
	public String toString() {
		return "AtchFileVO [atchFileNo=" + atchFileNo + ", sn=" + sn + ", atchFileNm=" + atchFileNm
				+ ", orignAtchFileNm=" + orignAtchFileNm + ", atchFileSize=" + atchFileSize + ", extsn=" + extsn
				+ ", atchFile=" + atchFile + ", fileCours=" + fileCours + "]";
	}
	
}
