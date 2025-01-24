package careneighbors.guardian;

import careneighbors.caregiver.Caregiver;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
    boolean existsByResidentNumberOrPhoneNumber(String residentNumber, String phoneNumber);



}
