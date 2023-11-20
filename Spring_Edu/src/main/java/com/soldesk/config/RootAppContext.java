package com.soldesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.soldesk.beans.DataBean1;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class RootAppContext {
	
	@Bean
	@SessionScope // 새로운 요청이 발생되면 호출되는 메소드
	public DataBean1 dataBean1() {
		
		return new DataBean1();
	}

}
