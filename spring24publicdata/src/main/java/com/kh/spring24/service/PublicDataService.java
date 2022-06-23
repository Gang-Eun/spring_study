package com.kh.spring24.service;

import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kh.spring24.vo.Covid19ChartVO;
import com.kh.spring24.vo.Covid19VO;
import com.kh.spring24.vo.HospitalResponseVO;
import com.kh.spring24.vo.HospitalVO;

public interface PublicDataService {
	Covid19VO getCovid19ConfirmData() throws URISyntaxException, JsonMappingException, JsonProcessingException; 
	Covid19ChartVO getCovid19ConfrimChartData() throws URISyntaxException, JsonMappingException, JsonProcessingException; 
	
	List<HospitalVO> getChungBukHospital() throws URISyntaxException, JsonMappingException, JsonProcessingException;
}
