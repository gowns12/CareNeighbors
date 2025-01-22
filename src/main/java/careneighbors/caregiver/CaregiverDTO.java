package careneighbors.caregiver;

public record CaregiverDTO(
        String nationality,
        String language,
        String affiliation,
        String workPlace,
        String name,
        Integer registrationNumber,
        Integer age,
        String contactNumber,
        String address,
        String imageUrl,
        boolean blackList
) {
    public static Caregiver toEntity(CaregiverDTO dto) {
        Caregiver caregiver = new Caregiver();
        updateEntity(caregiver, dto);
        return caregiver;
    }

    public static void updateEntity(Caregiver caregiver, CaregiverDTO dto) {
        caregiver.setNationality(dto.nationality());
        caregiver.setLanguage(dto.language());
        caregiver.setAffiliation(dto.affiliation());
        caregiver.setWorkPlace(dto.workPlace());
        caregiver.setName(dto.name());
        caregiver.setRegistrationNumber(dto.registrationNumber());
        caregiver.setAge(dto.age());
        caregiver.setContactNumber(dto.contactNumber());
        caregiver.setAddress(dto.address());
        caregiver.setImageUrl(dto.imageUrl());
        caregiver.setBlackList(dto.blackList());
    }

    public static CaregiverDTO fromEntity(Caregiver caregiver) {
        return new CaregiverDTO(
                caregiver.getNationality(),
                caregiver.getLanguage(),
                caregiver.getAffiliation(),
                caregiver.getWorkPlace(),
                caregiver.getName(),
                caregiver.getRegistrationNumber(),
                caregiver.getAge(),
                caregiver.getContactNumber(),
                caregiver.getAddress(),
                caregiver.getImageUrl(),
                caregiver.isBlackList()
        );
    }
}
