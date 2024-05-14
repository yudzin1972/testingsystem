package by.yudzin.testingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"by"})
@EnableAsync
public class TestingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingsystemApplication.class, args);
	}
}
