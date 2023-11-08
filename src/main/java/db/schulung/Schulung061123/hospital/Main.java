package db.schulung.Schulung061123.hospital;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
//                new ClassPathXmlApplicationContext("spring.xml");
                new AnnotationConfigApplicationContext(Main.class);



        Doctor doctor = context.getBean(Doctor.class);
        System.out.println(doctor.assist());
        System.out.println("################");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
