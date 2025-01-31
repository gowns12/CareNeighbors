package careneighbors.guardian;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @NonNull
    @Column(nullable = false, unique = true)
    private String residentNumber;

    @NonNull
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean isBlackList = false;

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuardianPatient> guardianPatients = new ArrayList<>();

//    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
//    private List<Caregiver> caregivers = new ArrayList<>();
//
//    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Hospital> hospitals = new ArrayList<>();

    public Boolean getBlackList() {
        return isBlackList;
    }


}





