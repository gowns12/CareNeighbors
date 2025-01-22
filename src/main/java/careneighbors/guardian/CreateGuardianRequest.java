package careneighbors.guardian;

import jakarta.validation.constraints.NotBlank;

public record CreateGuardianRequest(

        @NotBlank String name,
        @NotBlank String phoneNumber,
        @NotBlank String Location,
        @NotBlank String ResidentNumber


) {
}
