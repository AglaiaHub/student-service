package telran.java51.student.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java51.student.dao.StudentRepository;
import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;
import telran.java51.student.dto.exeptions.StudentNotFoundException;
import telran.java51.student.model.Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    final StudentRepository studentRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addStudent(StudentCreateDto studentCreateDto) {
        if(studentRepository.existsById(studentCreateDto.getId())){
            return false;
        }
//        Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(), studentCreateDto.getPassword());
        Student student = modelMapper.map(studentCreateDto, Student.class); // работает на рефлекции ??? что это значит???
        studentRepository.save(student);
        return null;
    }

    @Override
    public StudentDto findStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return modelMapper.map(student, StudentDto.class);
//        return new StudentDto(id, student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(id, student.getName(), student.getScores());
    }

    @Override
    public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.save(new Student(id, studentUpdateDto.getName(), studentUpdateDto.getPassword()));
        return new  StudentCreateDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow();
        boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return res;
    }

    @Override
    public List<StudentDto> findStudentByName(String name) {
        return studentRepository.findByNameIgnoreCase(name)
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .collect(Collectors.toList());
    }

    @Override
    public Long getStudentsNameQuantity(Set<String> names) {
        return studentRepository.countByNameInIgnoreCase(names);
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
        return null;
    }
}
