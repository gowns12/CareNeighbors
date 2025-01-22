package careneighbors.patient;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String residentNumber;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = true)
    private String medicalConditions;
    Boolean isBlackList = false;

    public Patient(String name, String gender, String residentNumber, String phoneNumber, String medicalConditions) {
        this.name = name;
        this.gender = gender;
        this.residentNumber = residentNumber;
        this.phoneNumber = phoneNumber;
        this.medicalConditions = medicalConditions;
    }

    public void update(PatientRequest rq) {
        name = rq.name();
        gender = rq.gender();
        residentNumber = rq.phoneNumber();
        phoneNumber = rq.phoneNumber();
        medicalConditions = rq.medicalConditions();
    }
}
