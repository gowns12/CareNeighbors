//package careneighbors.education;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@ToString
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class QuizQuestion {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String question;
//
//    @ElementCollection
//    private List<String> options = new ArrayList<>();
//
//    private Integer correctOptionIndex;
//
//    @ManyToOne
//    private QuizContent quizContent;
//
//}
