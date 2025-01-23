package careneighbors.patient;

import java.util.List;

public record PatientResponse(
        Long id,
        String name,
        Integer age,
        String gender,
        String residentNumber,
        String phoneNumber,
        String medicalConditions
//        List<String> hospitalNames,
//        List<String> caregiverNames,
//        List<String> guardianNames
) {
}
