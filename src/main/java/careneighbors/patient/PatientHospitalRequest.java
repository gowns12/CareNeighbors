package careneighbors.patient;
//todo test코드 작성용
public record PatientHospitalRequest (Long id,
                                      String name,
                                      String gender,
                                      String residentNumber,
                                      String phoneNumber,
                                      String medicalConditions,
                                      String hospitalName){
}
