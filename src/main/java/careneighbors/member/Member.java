package careneighbors.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Member {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String gender;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String eMail;
    @NonNull
    String address;
    @NonNull
    String affiliation;
}
