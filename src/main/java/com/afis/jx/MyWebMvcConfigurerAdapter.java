package com.afis.jx;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.afis.jx.storage.StorageProperties;

/**
 * Created by wanghui on 16-11-25.
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.htm");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.htm");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.htm");
			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.htm");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 对上传的图片路径进行映射
		registry.addResourceHandler(storageProperties.getVirtualPath() + "**")
				.addResourceLocations("file:" + storageProperties.getLocation());
		super.addResourceHandlers(registry);// ResourceHttpRequestHandler
	}
	
	@Bean
    public MultipartConfigElement multipartConfigElement() { 
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(storageProperties.getMaxFileSize());
        factory.setMaxRequestSize(storageProperties.getMaxFileSize());
        return factory.createMultipartConfig();
	}
}
