package careneighbors.patient;

import careneighbors.hospital.Hospital;
import jakarta.persistence.*;

@Entity
public class PatientHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Hospital hospital;
}
