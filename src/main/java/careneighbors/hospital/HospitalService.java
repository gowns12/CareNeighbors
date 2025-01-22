package careneighbors.hospital;

import careneighbors.hospital.exception.HospitalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital createHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = new Hospital(
                hospitalRequest.companyName(),
                hospitalRequest.address(),
                hospitalRequest.contactNumber(),
                hospitalRequest.bedCount(),
                hospitalRequest.website(),
                hospitalRequest.imageUrl()
                );
        return hospitalRepository.save(hospital);
    }

    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(o->HospitalResponse.toDto(o)
                )
                .toList();
    }

    public HospitalResponse getHospitalById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));
    }

    public Hospital updateHospital(Long id, HospitalResponse hospitalResponse) {
        Hospital existingHospital = getHospitalById(id);
        existingHospital.update();
        return hospitalRepository.save(existingHospital);
    }

    public void deleteHospital(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new HospitalNotFoundException("Hospital not found with id: " + id);
        }
        hospitalRepository.deleteById(id);
    }
}
