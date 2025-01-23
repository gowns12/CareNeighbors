package careneighbors.hospital;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record HospitalRequest(
        @NotNull @Size(min = 2)
        String companyName,
        @NotNull
        String address,
        @NotNull @Size(min = 10, max = 13)
        String contactNumber,
        @NotNull @Positive
        Integer registrationNumber,
        @NotNull
        String type,
        @NotNull @Positive
        Integer bedCount,
        String website,
        String imageUrl
) {
}
