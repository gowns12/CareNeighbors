package careneighbors.guardian;

import careneighbors.patient.Patient;
import jakarta.persistence.*;

public class GuardianPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Guardian guardian;

    @ManyToOne
    private Patient patient;




}
