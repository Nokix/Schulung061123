package db.schulung.Schulung061123.service;

import db.schulung.Schulung061123.entity.Student;
import db.schulung.Schulung061123.generator.Generator;
import db.schulung.Schulung061123.repo.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    Generator generator;

    @Test
    void createRandomStudentsTest() {
        Mockito.when(generator.name())
                .thenReturn("Hans")
                .thenReturn("Sandra")
                .thenReturn("Thomas");

        Mockito.when(studentRepository.save(Mockito.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        List<Student> randomsStudents = studentService.createRandomsStudents(3);

        List<String> resultedNames = randomsStudents.stream().map(Student::getName).toList();
        List<String> expectedNames = List.of("Hans", "Sandra", "Thomas");

        assertIterableEquals(expectedNames, resultedNames);
    }
}