package careneighbors.hospital.hospitalDto;


import careneighbors.hospital.Hospital;

public record HospitalResponse(
        Long id,
        String companyName,
        String address,
        String contactNumber,
        String registrationNumber,
        String type,
        Integer bedCount,
        String website,
        String imageUrl
) {
    public static HospitalResponse toDto(Hospital hospital){
        return new HospitalResponse(
                hospital.getId(),
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