package br.com.engdb.services.devportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import br.com.engdb.services.commons.UiSecurityConfig;

/**
 * Classe de Start da aplicacao - Dev Portal Rest Service
 * 
 * @version 1.0
 */
@SpringBootApplication
@Import({
	UiSecurityConfig.class})
public class DevPortalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DevPortalApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DevPortalApplication.class);
	}
	
}
