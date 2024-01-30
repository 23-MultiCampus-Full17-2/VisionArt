package com.mc.full17th2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //servlet-context.xml 대신 현재 클래스 설정함.
public class MyPathConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/visionart/**").addResourceLocations("file:///c:/visionart/");
		
	}
	//http://ip:port/visionart/파일명

}
