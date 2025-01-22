package careneighbors.caregiver;

public record CaregiverRequest(
        String nationality,
        String language,
        String affiliation,
        String workPlace,
        String name,
        Integer registrationNumber,
        Integer age,
        String contactNumber,
        String address,
        String imageUrl
) {
    public CaregiverDTO toDTO() {
        return new CaregiverDTO(
                nationality,
                language,
                affiliation,
                workPlace,
                name,
                registrationNumber,
                age,
                contactNumber,
                address,
                imageUrl,
                false  // 기본값으로 blackList를 false로 설정
        );
    }
}