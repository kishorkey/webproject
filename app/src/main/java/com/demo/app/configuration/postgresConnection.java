package com.demo.app.configuration;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class postgresConnection {
	
	@Bean
	public DataSource datasource() throws PropertyVetoException {
		
		String username = "postgres";
		String password = "superuser";
		
		final DriverManagerDataSource datasource = new DriverManagerDataSource();
		
		      datasource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		      datasource.setUsername(username);
		      datasource.setPassword(password);
		      
		      return datasource;	
	}
	

}
