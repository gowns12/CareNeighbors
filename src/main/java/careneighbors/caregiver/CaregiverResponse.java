package careneighbors.caregiver;

public record CaregiverResponse(
        Long id,
        String nationality,
        String language,
        String affiliation,
        String workPlace,
        String name,
        String registrationNumber,
        String contactNumber,
        String address,
        String imageUrl
) {
    public static CaregiverResponse toDto(Caregiver caregiver) {
        return new CaregiverResponse(
                caregiver.getId(),
                caregiver.getNationality(),
                caregiver.getLanguage(),
                caregiver.getAffiliation(),
                caregiver.getWorkPlace(),
                caregiver.getName(),
                caregiver.getRegistrationNumber(),
                caregiver.getContactNumber(),
                caregiver.getAddress(),
                caregiver.getImageUrl()
        );
    }
}
