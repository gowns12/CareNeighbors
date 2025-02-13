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

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }
}
