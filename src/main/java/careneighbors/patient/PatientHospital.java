package careneighbors.patient;

import careneighbors.hospital.Hospital;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class PatientHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Hospital hospital;


    public PatientHospital() {
    }

    public PatientHospital(PatientRequest patient, Hospital hospital) {

    }
    public PatientHospital(PatientResponse patientResponse , Hospital hospital) {

    }
}
