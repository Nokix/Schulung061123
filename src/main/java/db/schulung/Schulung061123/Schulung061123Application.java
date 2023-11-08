package db.schulung.Schulung061123;

import db.schulung.Schulung061123.hospital.Doctor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Schulung061123Application {

    public static void main(String[] args) {
        SpringApplication.run(Schulung061123Application.class, args);
    }

}
