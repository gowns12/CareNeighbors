package careneighbors.member;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MemberRequestDto(
        @NotNull
        String name,
        @NotNull
        String gender,
        @NotNull
        String phoneNumber,
        String eMail,
        @NotNull
        String address,
        @NotNull
        String affiliation
) {
    public Member toEntity() {
        return new Member(name, gender, phoneNumber, eMail, address, affiliation);
    }
}
