package careneighbors.hospital;

import careneighbors.hospital.exception.HospitalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalResponse create(HospitalRequest rq) {
        Hospital hospital = new Hospital(
                rq.companyName(),
                rq.address(),
                rq.contactNumber(),
                rq.registrationNumber(),
                rq.type(),
                rq.bedCount(),
                rq.website(),
                rq.imageUrl()
        );
        hospitalRepository.save(hospital);
        return HospitalResponse.toDto(hospital);
    }

    public List<HospitalResponse> readAll() {
        return hospitalRepository.findAll().stream()
                .map(HospitalResponse::toDto)
                .toList();
    }

    public HospitalResponse read(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + hospitalId));
        return HospitalResponse.toDto(hospital);
    }

    @Transactional
    public void update(Long hospitalId, HospitalRequest rq) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + hospitalId));
        hospital.update(rq);
    }

    public void delete(Long hospitalId) {
        hospitalRepository.deleteById(hospitalId);
    }
}
