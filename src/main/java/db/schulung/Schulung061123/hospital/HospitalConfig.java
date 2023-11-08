package db.schulung.Schulung061123.hospital;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HospitalConfig {

    @Bean
    public String getName() {
        return "Riccarda";
    }

    @Bean
    public String getName2() {
        return "Sergei";
    }

    @Bean
    public String firstName() {
        return "Thomas";
    }

    @Bean
    public String lastName() {
        return "Ohlbach";
    }

    @Bean
    public String name(String firstName,
                String lastName) {
        return firstName + " " + lastName;
    }
}
