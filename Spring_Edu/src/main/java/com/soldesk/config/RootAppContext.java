package com.soldesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.soldesk.beans.DataBean1;

// ������Ʈ �۾��� ����� bean�� �����ϴ� Ŭ����
@Configuration
public class RootAppContext {
	
	@Bean
	@SessionScope // ���ο� ��û�� �߻��Ǹ� ȣ��Ǵ� �޼ҵ�
	public DataBean1 dataBean1() {
		
		return new DataBean1();
	}

}
