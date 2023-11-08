package db.schulung.Schulung061123.hospital;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Setter
public class Doctor {
    String name;
    Helper helper;

    public Doctor(
            @Value("#{hospitalConfig.lastName()}") String name,
            Helper helper) {
        this.name = name;
        this.helper = helper;
    }

    public String assist() {
        return "Dr. " + this.name + " is helping. "
                + this.helper.assist(); //Strg+Shift+Enter
    }
}
