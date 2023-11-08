package db.schulung.Schulung061123.rest;

import db.schulung.Schulung061123.entity.Student;
import db.schulung.Schulung061123.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("students")
    List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    List<Student> createRandomStudents(@RequestParam int amount) {
        if (amount < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "amount cannot be negative.");
        }
        return studentService.createRandomsStudents(amount);
    }

    @PostMapping("students/new")
    @ResponseStatus(HttpStatus.CREATED)
    Student saveStudent(@RequestBody @Valid Student student) {
        return studentService.saveStudent(student);
    }
}
