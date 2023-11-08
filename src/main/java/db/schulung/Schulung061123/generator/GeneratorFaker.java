package db.schulung.Schulung061123.generator;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
public class GeneratorFaker implements Generator{
    Faker faker;

    public GeneratorFaker(Faker faker) {
        this.faker = faker;
    }

    @Override
    public String name() {
        return faker.name().fullName();
    }
}
