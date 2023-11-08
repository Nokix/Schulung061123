package db.schulung.Schulung061123.runner;

import db.schulung.Schulung061123.entity.Student;
import db.schulung.Schulung061123.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("jpa")
public class DatabaseRunner implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hallo, es geht los");

        studentRepository.save(new Student("Karlos"));
        studentRepository.save(new Student("Dia"));
        studentRepository.save(new Student("Marcel"));

        List<Student> students = studentRepository.findByName("Dia");
        students.forEach(System.out::println);
    }
}
