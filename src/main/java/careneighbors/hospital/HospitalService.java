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

    public Hospital createHospital(HospitalDTO hospitalDTO) {
        Hospital hospital = HospitalDTO.toEntity(hospitalDTO);
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));
    }

    public Hospital updateHospital(Long id, HospitalDTO hospitalDTO) {
        Hospital existingHospital = getHospitalById(id);
        HospitalDTO.updateEntity(existingHospital, hospitalDTO);
        return hospitalRepository.save(existingHospital);
    }

    public void deleteHospital(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new HospitalNotFoundException("Hospital not found with id: " + id);
        }
        hospitalRepository.deleteById(id);
    }
}
