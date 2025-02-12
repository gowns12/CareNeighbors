package careneighbors.hospital.hospitalDto;

import careneighbors.hospital.Hospital;
import jakarta.transaction.Transactional;

public record HospitalRequest(
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

}