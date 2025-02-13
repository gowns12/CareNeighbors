//package careneighbors.education;
//
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;
//import lombok.*;
//
//import java.util.ArrayList;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@ToString
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class QuizContent extends EducationContent {
//    @OneToMany(mappedBy = "quizContent", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<QuizQuestion> questions = new ArrayList<>();
//
//
//}