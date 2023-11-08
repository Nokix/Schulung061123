package db.schulung.Schulung061123.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.schulung.Schulung061123.entity.Student;
import db.schulung.Schulung061123.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    private static Stream<Object[]> randomStuds() {
        List<Student> students = List.of(
                new Student("Stefan"),
                new Student("Marcel"),
                new Student("Jonas")
        );

        return Stream.of(0, 1, 2, 3)
                .map(n -> new Object[]{n, students.subList(0, n)});
    }

    @ParameterizedTest
    @MethodSource("randomStuds")
    void createRandomStudentsTest(int amount, List<Student> studs) throws Exception {
        RequestBuilder post = MockMvcRequestBuilders
                .post("/students/create")
                .param("amount", String.valueOf(amount));

        Mockito.when(studentService.createRandomsStudents(amount))
                .thenReturn(studs);

        String expectedJson = objectMapper.writeValueAsString(studs);

        mockMvc.perform(post)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    @Test
    void whenAmountIsNegative_thenBadRequest() throws Exception {
        int invalidAmount = -1;
        RequestBuilder post = MockMvcRequestBuilders
                .post("/students/create")
                .param("amount", String.valueOf(invalidAmount));
        mockMvc.perform(post)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//                .andExpect(MockMvcResultMatchers.content().string("amount cannot be negative."));
    }

    @Test
    void saveStudentTest() throws Exception {
        Student student = new Student("Jonas");
        String studentJson = objectMapper.writeValueAsString(student);

        RequestBuilder post = MockMvcRequestBuilders
                .post("/students/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson);

        Student resultStudent = student.setId(1L);
        Mockito.when(studentService.saveStudent(Mockito.any()))
                .thenReturn(resultStudent);

        String resultStudentJson = objectMapper.writeValueAsString(resultStudent);

        mockMvc.perform(post)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(resultStudentJson));
    }
}