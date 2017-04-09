package com.SpringBootAndAngularspringRestquerydsljmapperproject;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@ComponentScan(basePackages = { "com.elsewedy" })
//@PropertySource("application.properties")
public class SpringBootAndAngularspringRestquerydsljmapperproject extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndAngularspringRestquerydsljmapperproject.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootAndAngularspringRestquerydsljmapperproject.class);
    }
	
	
	 
	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .username("root")
	        .password("root")
	        .url("jdbc:mysql://localhost:3306/elsewedy")
	        .driverClassName("com.mysql.jdbc.Driver")
	        .build();
	}
}
