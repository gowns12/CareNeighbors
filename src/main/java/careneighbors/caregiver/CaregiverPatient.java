package careneighbors.caregiver;

import careneighbors.patient.Patient;
import jakarta.persistence.*;

@Entity
public class CaregiverPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Caregiver caregiver;
}
