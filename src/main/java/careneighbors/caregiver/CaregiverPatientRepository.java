package careneighbors.caregiver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaregiverPatientRepository extends JpaRepository<CaregiverPatient,Long> {
    CaregiverPatient findByPatientId();

    List<CaregiverPatient> findAllByPatientId(Long patientId);
}
