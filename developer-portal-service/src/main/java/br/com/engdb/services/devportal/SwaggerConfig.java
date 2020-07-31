package br.com.engdb.services.devportal;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.engdb.services.commons.tools.RedirectFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {       
	
	@Value( "${app.version}" )
	private String appVersion;
	
	@Value( "${server.servlet.context-path}" )
	private String contextPath;
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()     
          .apis(RequestHandlerSelectors.basePackage("br.com.engdb.services.devportal.controller"))              
          .paths(PathSelectors.any())    
          .build()
          .apiInfo(apiInfo());                                           
    }
    
    @Bean
    public FilterRegistrationBean<RedirectFilter> filterRegistrationBean(){
        FilterRegistrationBean<RedirectFilter> filterRegistrationBean = new FilterRegistrationBean<RedirectFilter>();
        filterRegistrationBean.setFilter(new RedirectFilter(contextPath, contextPath + "/swagger-ui.html"));
        return filterRegistrationBean;
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "Dev Portal Rest Service", 
          "Serviço responsável por prover as funcionalidades para o Dev Portal", 
          appVersion, 
          "Todos os direitos reservado Engdb", 
          new Contact("Contato Engdb", "https://www.engdb.com.br/", "contato@engdb.com.br"), 
          "Informações de licença deverão ser requisitadas por contato.", "https://www.engdb.com.br/", Collections.emptyList());
    }
    
}