package com.kh.spring24;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.kh.spring24.vo.Covid19ResponseVO;
import com.kh.spring24.vo.Covid19VO;
import com.kh.spring24.vo.HospitalResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Spring24publicdataApplicationTests {

//	@Test
	void contextLoads() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		// Java에서 Http 요청을 발생시켜 공공데이터포탈의 데이터를 조회
		// 1. HttpUrlConnection 사용(자바 표준 API)
		// 2. OkHttp3 라이브러리 사용(외부 라이브러리)
		// 3. RestTemplate 사용(Spring 라이브러리)

		RestTemplate template = new RestTemplate();

		String serviceKey = "o4oyliaEpeqqW9cG5RbqsTd%2Bjdqi0OgdSoCMrBBWujZ7TQrkJdVAlSphr%2B97J%2FU%2BB6f97Rc4f1%2BDWUA59RCYow%3D%3D";
		String endpoint = "http://apis.data.go.kr/1790387/covid19CurrentStatusConfirmations/covid19CurrentStatusConfirmationsJson";
		String queryString = "?serviceKey=" + serviceKey;

		URI uri = new URI(endpoint + queryString);

		String text = template.getForObject(uri, String.class);
//				log.info("text = {}", text);
//				ResponseEntity<String> entity = template.getForEntity(uri, String.class);
//				log.info("entity = {}", entity);

//				error
//				Covid19VO responseVO = 
//						template.getForObject(uri, Covid19VO.class);
		ObjectMapper mapper = new ObjectMapper();
		Covid19VO responseVO = mapper.readValue(text, Covid19VO.class);
		log.info("responseVO = {}", responseVO);
	}
	
	@Test
	public void hospital() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate template = new RestTemplate();
		URI uri = new URI("https://www.chungbuk.go.kr/openapi-json/pubdata/pubMapEmergency.do?numOfRows=1000&pageNo=1");
		String result = template.getForObject(uri, String.class);
		result = result.replace("\\", "");
		result = result.substring(1, result.length() -1);
		log.info("result = {}", result);
		
		ObjectMapper mapper = JsonMapper.builder()
										.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
										.build();
		HospitalResponseVO responseVO = mapper.readValue(result, HospitalResponseVO.class);
		log.info("responseVO = {}", responseVO);
	}
}
