package careneighbors.caregiver;

import careneighbors.caregiver.exception.CaregiverNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;

    public CaregiverResponse create(CaregiverRequest rq) {
        Caregiver caregiver = new Caregiver(
                rq.nationality(),
                rq.language(),
                rq.affiliation(),
                rq.workPlace(),
                rq.name(),
                rq.registrationNumber(),
                rq.contactNumber(),
                rq.address(),
                rq.imageUrl()
        );
        caregiverRepository.save(caregiver);
        return CaregiverResponse.toDto(caregiver);
    }

    public List<CaregiverResponse> readAll() {
        return caregiverRepository.findAll().stream()
                .map(CaregiverResponse::toDto)
                .toList();
    }

    public CaregiverResponse read(Long caregiverId) {
        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new CaregiverNotFoundException("Caregiver not found with id: " + caregiverId));
        return CaregiverResponse.toDto(caregiver);
    }

    @Transactional
    public void update(Long caregiverId, CaregiverRequest rq) {
        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new CaregiverNotFoundException("Caregiver not found with id: " + caregiverId));
        caregiver.update(rq);
    }

    public void delete(Long caregiverId) {
        caregiverRepository.deleteById(caregiverId);
    }
}
