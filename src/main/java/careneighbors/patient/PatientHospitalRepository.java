package careneighbors.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHospitalRepository extends JpaRepository<PatientHospital, Long> {
}
