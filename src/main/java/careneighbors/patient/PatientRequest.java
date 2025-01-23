package careneighbors.patient;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record PatientRequest(
        @NotNull@Size(min = 2)
        String name,
        @NotNull
        String gender,
        @NotNull@Size(min = 14,max = 14)
        String residentNumber,
        @Size(min = 10,max = 13)
        String phoneNumber,
        //보유 질환 정보(EX - 거동 불편, 고혈압, 치매, 천식 등)
        String medicalConditions,
        @NotNull
        String hospitalName
) {
}
