package careneighbors.caregiver;

import careneighbors.caregiver.exception.CaregiverNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    @Transactional
    public Caregiver createCaregiver(CaregiverRequest caregiverRequest) {
        Caregiver caregiver = new Caregiver(
                caregiverRequest.affiliation(),
                caregiverRequest.workPlace(),
                caregiverRequest.address(),
                caregiverRequest.imageUrl()
        );
        return caregiverRepository.save(caregiver);
    }

    public List<Caregiver> getAllCaregivers() {
        return caregiverRepository.findAll();
    }

    public Caregiver getCaregiverById(Long id) {
        return caregiverRepository.findById(id)
                .orElseThrow(() -> new CaregiverNotFoundException("Caregiver not found with id: " + id));
    }

    @Transactional
    public Caregiver updateCaregiver(Long id, CaregiverRequest caregiverRequest) {
        Caregiver existingCaregiver = getCaregiverById(id);
        existingCaregiver.updata(caregiverRequest);
        return caregiverRepository.save(existingCaregiver);
    }

    @Transactional
    public void deleteCaregiver(Long id) {
        caregiverRepository.deleteById(id);
    }
}