package telran.java51.student.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;


@Getter
@EqualsAndHashCode(of = "id")
@Document(collection = "students")
@NoArgsConstructor
public class Student {
    @Setter
    int id;
    @Setter
    String name;
    @Setter
    String password;
    Map<String, Integer> scores = new HashMap<>();

    public Student(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
        this.scores = new HashMap<>();
    }

    public boolean addScore(String exam, int score){
        return scores.put(exam,score) == null;
    }
}
