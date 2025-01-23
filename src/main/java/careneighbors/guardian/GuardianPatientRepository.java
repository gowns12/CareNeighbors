package careneighbors.guardian;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianPatientRepository extends JpaRepository<GuardianPatient, Long> {
    List<GuardianPatient> findByPatientId(Long patientId);
    List<GuardianPatient> findByPatient_GuardianId(Long guardianId);

    List<GuardianPatient> findAllByGuardianId(Long guardianId);
}
