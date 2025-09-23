package pe.upc.vacappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VacappBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacappBackendApplication.class, args);
    }

}
