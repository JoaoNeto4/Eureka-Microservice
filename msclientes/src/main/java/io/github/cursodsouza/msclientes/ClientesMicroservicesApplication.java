package io.github.cursodsouza.msclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientesMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesMicroservicesApplication.class, args);
	}

}
