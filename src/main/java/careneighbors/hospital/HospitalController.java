package careneighbors.hospital;


import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createHospital(@RequestBody HospitalRequest rq) {
        hospitalService.createHospital(rq);
    }
    //TOdo 병원 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalResponse> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
    //Todo id로 병원 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospitalResponse getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
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
}