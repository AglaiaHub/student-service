package telran.java51.student.service;

import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentCreateDto studentCreateDto);

    StudentDto findStudent(Integer id);

    StudentDto removeStudent(Integer id);

    StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

    Boolean addScore (Integer id, ScoreDto scoreDto);

    List<StudentDto> findStudentByName (String name);

    Long getStudentsNameQuantity(Set<String> names);

    List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore);

}
