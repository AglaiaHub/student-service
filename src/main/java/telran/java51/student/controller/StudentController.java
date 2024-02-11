package telran.java51.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;
import telran.java51.student.service.StudentService;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class StudentController {
    final StudentService studentService;
    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
        return studentService.addStudent(studentCreateDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Integer id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{studentId}")
    public StudentDto removeStudent(@PathVariable("studentId") Integer id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentCreateDto updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PutMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentByName(@PathVariable String name) {
        return studentService.findStudentByName(name);
    }

    @PostMapping("/quantity/students")
    public Long getStudentsNameQuantity(@RequestBody Set<String> names) {
        return studentService.getStudentsNameQuantity(names);
    }

    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public List<StudentDto> getStudentsByExamMinScore(@PathVariable String exam, @PathVariable Integer minScore) {
        return studentService.getStudentsByExamMinScore(exam,minScore);
    }
}
