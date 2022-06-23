package com.kh.spring24.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class HospitalVO {
	private int fcNo;
	private String fcNm;
	private int fcGbn;
	private String contents;
	private String fcAddr, fcAddrDetail;
	private String ref1, ref2, ref3;
	private String zip, xp, yp;
	private String siNm, sggNm, emdNm;
	
	// Jackson에게 없어도 되는 항목임을 알려준다.
	@JsonIgnore
	private String liNm;
	private String wDate; 
}
