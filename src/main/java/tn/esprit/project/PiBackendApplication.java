package tn.esprit.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.esprit.project.Service.RecommandationService;

@SpringBootApplication
@EnableJpaRepositories


public class PiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiBackendApplication.class, args);

	}

}
