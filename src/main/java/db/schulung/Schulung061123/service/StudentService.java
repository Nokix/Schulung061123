package db.schulung.Schulung061123.service;

import com.github.javafaker.Faker;
import db.schulung.Schulung061123.entity.Student;
import db.schulung.Schulung061123.generator.Generator;
import db.schulung.Schulung061123.repo.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public final Generator generator;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> createRandomsStudents(int amount) {
        return Stream
                .generate(generator::name)
                .limit(amount)
                .map(Student::new)
                .map(studentRepository::save)
                .toList();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
