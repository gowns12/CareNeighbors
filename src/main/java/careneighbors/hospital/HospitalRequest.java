package careneighbors.hospital;

public record HospitalRequest(
        String companyName,
        String address,
        String contactNumber,
        String registrationNumber,
        String type,
        Integer bedCount,
        String website,
        String imageUrl
) {
    public HospitalResponse toDTO() {
        return new HospitalResponse(
                companyName,
                address,
                contactNumber,
                registrationNumber,
                type,
                bedCount,
                website,
                imageUrl
        );
    }
}