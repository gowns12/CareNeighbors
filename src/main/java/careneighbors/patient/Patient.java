package careneighbors.patient;

import careneighbors.guardian.GuardianPatient;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false,unique = true)
    private String residentNumber;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Column(nullable = true)
    private String medicalConditions;
    @Column(nullable = false)
    private Boolean isBlackList = false;

    public Patient(String name, String gender, String residentNumber, String phoneNumber, String medicalConditions) {
        this.name = name;
        this.gender = gender;
        this.residentNumber = residentNumber;
        this.phoneNumber = phoneNumber;
        this.medicalConditions = medicalConditions;
    }

    public void update(String name, String gender, String residentNumber, String phoneNumber, String medicalConditions) {
        this.name = name;
        this.gender = gender;
        this.residentNumber = residentNumber;
        this.phoneNumber = phoneNumber;
        this.medicalConditions = medicalConditions;
    }

}
