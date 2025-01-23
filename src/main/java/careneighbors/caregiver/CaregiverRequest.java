package careneighbors.caregiver;

public record CaregiverRequest(
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

}