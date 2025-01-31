package careneighbors.education;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VideoContent extends EducationContent {
    private String videoUrl;
    private Integer durationInSeconds;


}
