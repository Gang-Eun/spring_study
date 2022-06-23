package com.kh.spring24.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 해당 어노테이션 적으면 루트에 등록된다.  
public class SwaggerCustomConfiguration {

//	<bean id="passwordEndoer" class="어쩌구저쩌구.BcryptPasswordEncoder"/>
	
	
	// xml에서의 bean도 생성해주고, @Bean도 생성해준다. 
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	// => @Bean은 프로그래밍 코드가 들어간다. 하지만 xml에서는 프로그래밍 코드 작성 불가능 
	
	
	// <bean id="api" class="springfox.documentation.spring.web.plugins.Docket"/> 
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30) // 문서화 유형 설정 
				.select() // 추임새 
//					.apis(RequestHandlerSelectors.any()) // 분석할 클래스 유형
//					.apis(RequestHandlerSelectors.basePackage("com.kh.spring18.rest"))
					.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) // 어노테이션으로 불러오는 것 
					.paths(PathSelectors.any()) // 분석할 주소 유형  
				.build()
					.apiInfo( // 문서의 대표 정보 
						new ApiInfoBuilder()
								.title("Home REST API") // 문서의 제목 
								.description("제작한 홈페이지 내부 REST API") // 문서의 설명
								.version("0.0.1") // 버전 
								.license("MIT License") // 라이선스 정보를 알고 있어야 한다. 
							.build()
							)
					.useDefaultResponseMessages(false); // 기본 메세지 off  
	}
}
