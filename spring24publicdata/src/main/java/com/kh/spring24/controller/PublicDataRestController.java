package com.kh.spring24.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring24.service.PublicDataService;
import com.kh.spring24.vo.Covid19ChartVO;
import com.kh.spring24.vo.Covid19VO;
import com.kh.spring24.vo.HospitalResponseVO;
import com.kh.spring24.vo.HospitalVO;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class PublicDataRestController {

	
	@Autowired
	private PublicDataService publicDataService;
	
	// 프록시 서버 
	@GetMapping("/covid19/confirm")
	public Covid19VO covid19Confirm() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		return publicDataService.getCovid19ConfirmData();
	}
	
	@GetMapping("/covid19/confirm/chart")
	public Covid19ChartVO covid19ConfirmChart() throws JsonMappingException, JsonProcessingException, URISyntaxException {
		return publicDataService.getCovid19ConfrimChartData();
	}
	
	@GetMapping("/hospital/chungbuk")
	public List<HospitalVO> getHospitalListInChungBuk() throws JsonMappingException, JsonProcessingException, URISyntaxException {
		return publicDataService.getChungBukHospital();
	}
}
