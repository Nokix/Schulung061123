package db.schulung.Schulung061123.hospital;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class Handyman implements Helper{

    @Override
    public String assist() {
        return "Handyman is helping";
    }
}
