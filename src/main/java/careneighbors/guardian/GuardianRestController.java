package careneighbors.guardian;


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


}
