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

//    //Todo 병원비 내역
//    @PostMapping("/{hospitalname}/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void makebill(@RequestBody String hospitalname , @RequestParam Long id) {
//        hospitalService.makebill(hospitalname, id);
//    }
}