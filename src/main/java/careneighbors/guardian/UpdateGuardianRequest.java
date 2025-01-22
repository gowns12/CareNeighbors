package careneighbors.guardian;

public record UpdateGuardianRequest(
        String name,
        String phoneNumber,
        String ResidentNumber,
        String location
) {
}
