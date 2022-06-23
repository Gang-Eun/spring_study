package com.kh.spring24.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Covid19ChartVO {
	private List<String> label; 
	private List<Integer> count; 
	private List<Float> rate; 
}
