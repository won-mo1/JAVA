package kr.or.cnu.vo;

import lombok.Data;

@Data
public class MemVO {
	private String id;
	private String pwd;
	private String name;
	private String pne;
	private String email;
	
	
	@Override
	public String toString() {
		return "MemVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", pne=" + pne + ", email=" + email + "]";
	}
	
}
