package db.schulung.Schulung061123.hospital;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("nurse")
public class Nurse implements Helper {
    @Override
    public String assist() {
        return "Nurse is helping";
    }
}
