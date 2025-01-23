package careneighbors.guardian;


import careneighbors.caregiver.Caregiver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianRestController {

    private final GuardianService guardianService;

    public GuardianRestController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @PostMapping
    public void createGuardian(@RequestBody CreateGuardianRequest request) {
        guardianService.createGuardian(request);

    }

    @GetMapping
    public List<GuardianResponse> findAllGuardians() {
        return guardianService.findAllGuardians();
    }

    //특정 보호자 조회
    @GetMapping("/{guardianId}")
    public GuardianResponse findGuardiansByGuardianId(@PathVariable Long guardianId) {
        return guardianService.findGuardiansByGuardianId(guardianId);
    }

    //보호자 정보 수정
    @PutMapping("/{guardianId}")
    public void updateGuardian(@PathVariable Long guardianId, @RequestBody UpdateGuardianRequest request) {
        guardianService.updateGuardianRequest(guardianId, request);
    }

    //보호자 삭제
    @DeleteMapping("/{guardianId}")
    public void deleteByGuardianId(@PathVariable Long guardianId) {
        guardianService.deleteByGuardianId(guardianId);
    }

//    //간병인에게 선물하기 - 간병인 목록 조회/선택?
//    @GetMapping("/findAllCaregiver")
//    public List<Caregiver> findAllCaregiver(){
//        return guardianService.findAllCaregiver();
//    }

    //보호자가 환자 아이디로 환자의 간병인 조회

    @GetMapping("/caregiverIds/byGuardian/{guardianId}")
    public List<Long> findCaregiverIdsByGuardian(@PathVariable Long guardianId){
        return guardianService.findCaregiverIdsByGuardian(guardianId);
    }





    //간병인에게 선물하기 - 간병인 에게 선물하기
    @PostMapping("/giftCaregiver")
    public String giftCaregiver(@RequestBody GiftRequest giftRequest){
        return guardianService.giftCaregiver(giftRequest);
    }



}
