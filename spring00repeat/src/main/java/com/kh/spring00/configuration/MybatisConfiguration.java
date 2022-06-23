package com.kh.spring00.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class MybatisConfiguration {

	@Bean
	public SqlSessionFactoryBean factory(DataSource source, ApplicationContext ctx) throws IOException { // ctx : 스프링에서 제공하는 경로 탐색 도구 
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(source);
		bean.setConfigLocation(ctx.getResource("classpath:/mybatis/mybatis-config.xml"));
		bean.setMapperLocations(ctx.getResources("classpath:/mybatis/mapper/**/*-mapper.xml"));
		
		return bean; 
	}
	
	@Bean
	public SqlSessionTemplate template(SqlSessionFactory factory) {
		SqlSessionTemplate template = new SqlSessionTemplate(factory);
		
		return template; 
	}
}
