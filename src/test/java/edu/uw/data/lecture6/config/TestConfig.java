package edu.uw.data.lecture6.config;

import edu.uw.data.lecture6.dao.*;
import org.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;
/**
 * JAVA CONFIG
 */
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = "com.uw.data.lecture6.controller")
public class TestConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BookDao bookDao() {
		return Mockito.mock(BookDao.class);
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
}
