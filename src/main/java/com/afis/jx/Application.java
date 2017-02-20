package com.afis.jx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.afis.jx.ckfinder.connector.ConnectorServlet;

@SpringBootApplication(scanBasePackages = { "com.afis.jx" })
@EnableScheduling // 发现注解@Scheduled的任务并后台执行
public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		long end = System.currentTimeMillis();
		logger.info("============程序启动成功!      耗时间:{}毫秒==============", end - begin);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new ConnectorServlet(), "/ckfinder/core/*");// ServletName默认值为首字母小写，即myServlet
	}

}
