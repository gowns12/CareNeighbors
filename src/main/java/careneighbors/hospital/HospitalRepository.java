package careneighbors.hospital;

import careneighbors.hospital.hospitalDto.HospitalResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByName(String componyName);

    //Todo Name을 포함하는 병원 찾기
    List<HospitalResponse> findByNameContaining(String keyword);
}
