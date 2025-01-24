package careneighbors.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientHospitalRepository extends JpaRepository<PatientHospital, Long> {
    PatientResponse findByNameContaining(String name);
}
