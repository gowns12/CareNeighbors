package careneighbors.caregiver;

import java.util.List;

public record CaregiverPatientResponse(

        String caregiverName,
        Long caregiverId,
        List<String> patientNames

) {
}
