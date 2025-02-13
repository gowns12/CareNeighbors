package careneighbors.hospital.hospitalDto;

public record HospitalBillRequest (Long patientId,
                                   double treatmentCost,
                                   double roomCharge,
                                   double careCost) {
}
