package careneighbors.education;

import careneighbors.caregiver.Caregiver;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CaregiverEducationProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Caregiver caregiver;

    @ManyToOne
    private EducationContent educationContent;

    private boolean completed;
    private LocalDateTime completedAt;

    // For video content
    private Integer lastWatchedSecond;

    // For quiz content
    private Integer score;

}
