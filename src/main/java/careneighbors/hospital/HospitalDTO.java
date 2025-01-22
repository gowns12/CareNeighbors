package careneighbors.hospital;


public record HospitalDTO(
        String companyName,
        String address,
        String contactNumber,
        Integer registrationNumber,
        String type,
        Integer bedCount,
        String website,
        String imageUrl
) {
    public static Hospital toEntity(HospitalDTO dto) {
        Hospital hospital = new Hospital();
        updateEntity(hospital, dto);
        return hospital;
    }

    public static void updateEntity(Hospital hospital, HospitalDTO dto) {
        hospital.setCompanyName(dto.companyName());
        hospital.setAddress(dto.address());
        hospital.setContactNumber(dto.contactNumber());
        hospital.setRegistrationNumber(dto.registrationNumber());
        hospital.setType(dto.type());
        hospital.setBedCount(dto.bedCount());
        hospital.setWebsite(dto.website());
        hospital.setImageUrl(dto.imageUrl());
    }

    public static HospitalDTO fromEntity(Hospital hospital) {
        return new HospitalDTO(
                hospital.getCompanyName(),
                hospital.getAddress(),
                hospital.getContactNumber(),
                hospital.getRegistrationNumber(),
                hospital.getType(),
                hospital.getBedCount(),
                hospital.getWebsite(),
                hospital.getImageUrl()
        );
    }
}