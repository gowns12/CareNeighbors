package careneighbors.caregiver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CaregiverPatientRepository extends JpaRepository<CaregiverPatient,Long> {
}
