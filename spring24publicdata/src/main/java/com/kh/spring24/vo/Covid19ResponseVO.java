package com.kh.spring24.vo;

import lombok.Data;

@Data
public class Covid19ResponseVO {
	private int resultCnt, resultCode;
	private String resultMsg;
	private Covid19ResponseDataVO[] result;
}
