package enzoanthony.enzo_padroes_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EnzoPadroesSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnzoPadroesSpringApplication.class, args);
	}

}
