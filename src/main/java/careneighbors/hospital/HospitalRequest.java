package careneighbors.hospital;

public record HospitalRequest(
        String companyName,
        String address,
        String contactNumber,
        Integer registrationNumber,
        String type,
        Integer bedCount,
        String website,
        String imageUrl
) {
    public HospitalDTO toDTO() {
        return new HospitalDTO(
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