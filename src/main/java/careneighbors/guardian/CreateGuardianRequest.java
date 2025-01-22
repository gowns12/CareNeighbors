package careneighbors.guardian;

import jakarta.validation.constraints.NotBlank;

public record CreateGuardianRequest(

        @NotBlank String name,
        @NotBlank String phoneNumber,
        @NotBlank String location,
        @NotBlank String residentNumber


) {
}
