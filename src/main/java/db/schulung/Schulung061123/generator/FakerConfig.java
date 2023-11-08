package db.schulung.Schulung061123.generator;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfig {

    @Bean
    Faker faker() {
        return new Faker();
    }
}
