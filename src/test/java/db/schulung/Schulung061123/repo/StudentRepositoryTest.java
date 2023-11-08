package db.schulung.Schulung061123.repo;

import db.schulung.Schulung061123.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    private void setup() {
        testEntityManager.persist(new Student("Karl"));
        testEntityManager.persist(new Student("Eva"));
    }

    @Test
    void testFindByName() {
        List<Student> result = studentRepository.findByName("Karl");

        assertIterableEquals(
                List.of(new Student(1L, "Karl")),
                result);
    }
}