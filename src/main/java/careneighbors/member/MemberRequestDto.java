package careneighbors.member;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MemberRequestDto(
        @NotNull
        String name,
        @NotNull
        String gender,
        @NotNull@Min(8)@Max(12)
        String phoneNumber,
        String eMail,
        @NotNull
        String address,
        @NotNull
        String affiliation
) {
}
