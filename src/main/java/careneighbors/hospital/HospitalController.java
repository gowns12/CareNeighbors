package careneighbors.hospital;


import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import careneighbors.patient.PatientRequest;
import careneighbors.patient.PatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    //Todo 병원 추가
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createHospital(@RequestBody HospitalRequest rq) {
        hospitalService.createHospital(rq);
    }

    //TOdo 병원 목록 조회 (null 이면 전체 목록 조회 name 이들어가면 포함하는 결과 반환)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalResponse> getAllHospitals(@RequestParam(required = false) String name) {
        return hospitalService.getAllHospitals(name);
    }

    //Todo 병원 id 로 찾기
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospitalResponse getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalByNameAndId(id);
    }

    //Todo 병원 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateHospital(@PathVariable Long id, @RequestBody HospitalRequest rq) {
        hospitalService.updateHospital(id, rq);
    }

    //Todo 병원 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);

    }

    //Todo 환자 등록
    @PostMapping("/{id}/patient")
    @ResponseStatus(HttpStatus.OK)
    public void registerPatient(@PathVariable Long id, @RequestBody(required = false) PatientRequest patient) {
        hospitalService.registerPatient(id, patient);
    }

    //Todo 환자 이름으로 조회
    @GetMapping("/patient")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientResponse> getPatientList(@RequestParam(required = false) String name) {
        return hospitalService.getPatients(name);
    }

    //Todo 치료 비용 , 병상 비용 , 간병 비용 총합 병원비 내역
    @PostMapping("/{hospitalId}/bills/{guardianId}")
    public String createHospitalBill(
            @PathVariable Long hospitalId,      //TOdo 병원Id
            @PathVariable Long guardianId,      // 보호자 Id
            @RequestParam double treatmentCost, // 치료 발생 비용
            @RequestParam double roomCharge,    // 병상 발생 비용
            @RequestParam double careCost) {    // 간병인 고용 비용
        hospitalService.generateHospitalBill(hospitalId, guardianId, treatmentCost, roomCharge , careCost);
        return "병원비 내역 작성완료";
    }
}
