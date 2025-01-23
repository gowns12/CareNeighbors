package careneighbors.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public void createHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = new Hospital(
                hospitalRequest.companyName(),
                hospitalRequest.address(),
                hospitalRequest.contactNumber(),
                hospitalRequest.registrationNumber(),
                hospitalRequest.type(),
                hospitalRequest.bedCount()
                );
        hospitalRepository.save(hospital);
    }

    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(HospitalResponse::toDto
                )
                .toList();
    }

    public HospitalResponse getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + id));
        return HospitalResponse.toDto(hospital);
    }

    @Transactional
    public void updateHospital(Long id, HospitalRequest rq) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + id));
        hospital.update(rq);
    }

    public void deleteHospital(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new IllegalArgumentException("Hospital not found with id: " + id);
        }
        hospitalRepository.deleteById(id);
    }
}
