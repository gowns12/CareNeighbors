package careneighbors.caregiver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaregiverPatientRepository extends JpaRepository<CaregiverPatient,Long> {
    CaregiverPatient findByPatientId(Long patientId);

    List<CaregiverPatient> findAllByPatientId(Long patientId);
    List<CaregiverPatient> findAllByPatientIdIn(List<Long> patientIds);
}
