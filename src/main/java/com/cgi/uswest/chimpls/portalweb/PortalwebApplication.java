package com.cgi.uswest.chimpls.portalweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PortalwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalwebApplication.class, args);
	}
}
