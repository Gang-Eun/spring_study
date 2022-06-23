package com.kh.spring24.service;

import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kh.spring24.vo.Covid19ChartVO;
import com.kh.spring24.vo.Covid19VO;

public interface PublicDataService {
	Covid19VO getCovid19ConfirmData() throws URISyntaxException, JsonMappingException, JsonProcessingException; 
	Covid19ChartVO getCovid19ConfrimChartData() throws URISyntaxException, JsonMappingException, JsonProcessingException; 
}
