package tn.esprit.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.project.Service.RecommandationService;

@SpringBootApplication
public class PiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiBackendApplication.class, args);

	}

}
