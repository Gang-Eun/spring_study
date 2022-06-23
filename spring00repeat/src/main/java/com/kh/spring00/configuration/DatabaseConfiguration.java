package com.kh.spring00.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration // 설정파일임을 명시. 어떠한 클래스보다 빨리 실행된다. (프로젝트 준비 단계에서 실행된다.) 

// 등록된 properties 중에서 원하는 접두사로 가져오는 기능 (해당 변수에 Setter 메소드가 필요하다.) 
@ConfigurationProperties(prefix = "custom.database")
@Setter
public class DatabaseConfiguration {

	// @Autowired 불가능 (시점상 아직 생기지 않은 도구를 등록하는 거나 마찬가지이기 때문에 불가능하다.) 
	
//	@Bean
//	public 결과물 아이디(필요 의존성) {
//		
//	}
	
	private String driver, url, username, password; 
	private int maxTotal, maxIdle, maxWait;
	
	@Bean
	public BasicDataSource dbcpSource() { // BsicDataSource : apache-commons-dbcp2에서 지원해준다. 
//		log.info("driver = {}", driver);
//		log.info("maxTotal = {}", maxTotal);
		
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(driver);
		source.setUrl(url);
		source.setUsername(username);
		source.setPassword(password);
		
		source.setMaxTotal(maxTotal);
		source.setMaxIdle(maxIdle);
		source.setMaxWaitMillis(maxWait);
		
		return source; 
	}
}
