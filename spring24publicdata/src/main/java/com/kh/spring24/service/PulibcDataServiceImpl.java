package com.kh.spring24.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.kh.spring24.vo.Covid19ChartVO;
import com.kh.spring24.vo.Covid19ResponseDataVO;
import com.kh.spring24.vo.Covid19ResponseVO;
import com.kh.spring24.vo.Covid19VO;
import com.kh.spring24.vo.HospitalResponseVO;
import com.kh.spring24.vo.HospitalVO;

@Service
public class PulibcDataServiceImpl implements PublicDataService{
	
	@Value("${custom.public-data.key}")
	private String key; 

	@Override
	public Covid19VO getCovid19ConfirmData() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate template = new RestTemplate();

		String endpoint = "http://apis.data.go.kr/1790387/covid19CurrentStatusConfirmations/covid19CurrentStatusConfirmationsJson";
		String queryString = "?serviceKey=" + key;
		
		URI uri = new URI(endpoint + queryString);

		String text = template.getForObject(uri, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		Covid19VO responseVO = mapper.readValue(text, Covid19VO.class);
		
		return responseVO; 
	}
	
	@Override
	public Covid19ChartVO getCovid19ConfrimChartData() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate template = new RestTemplate();

		String endpoint = "http://apis.data.go.kr/1790387/covid19CurrentStatusConfirmations/covid19CurrentStatusConfirmationsJson";
		String queryString = "?serviceKey=" + key;
		
		URI uri = new URI(endpoint + queryString);

		String text = template.getForObject(uri, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		Covid19VO responseVO = mapper.readValue(text, Covid19VO.class);
		
		
		// ?????? : responseVO??? Covid19ChartVO??? ????????????.
		Covid19ResponseVO response = responseVO.getResponse();
		Covid19ResponseDataVO[] dataList = response.getResult();
		Covid19ResponseDataVO data = dataList[0];
		
		return Covid19ChartVO.builder()
								.label(List.of(data.getMmdd1(), data.getMmdd2(), data.getMmdd3(), data.getMmdd4(), data.getMmdd5(), data.getMmdd6(), data.getMmdd7(), data.getMmdd8()))
								.count(List.of(data.getCnt1(), data.getCnt2(), data.getCnt3(), data.getCnt4(), data.getCnt5(), data.getCnt6(), data.getCnt7(), data.getCnt8()))
								.rate(List.of(data.getRate1(), data.getRate2(), data.getRate3(), data.getRate4(), data.getRate5(), data.getRate6(), data.getRate7(), data.getRate8()))
							.build(); 
	}
	
	@Override
	public List<HospitalVO> getChungBukHospital() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		RestTemplate template = new RestTemplate();
		URI uri = new URI("https://www.chungbuk.go.kr/openapi-json/pubdata/pubMapEmergency.do?numOfRows=1000&pageNo=1");
		String result = template.getForObject(uri, String.class);
		result = result.replace("\\", "");
		result = result.substring(1, result.length() -1);
		
		ObjectMapper mapper = JsonMapper.builder()
										.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
										.build();
		HospitalResponseVO responseVO = mapper.readValue(result, HospitalResponseVO.class);
		
		return responseVO.getResponse(); 
	}
}
