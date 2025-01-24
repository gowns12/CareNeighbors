package careneighbors;


import careneighbors.caregiver.CaregiverRequest;
import careneighbors.guardian.CreateGuardianRequest;
import careneighbors.guardian.GiftRequest;
import careneighbors.guardian.UpdateGuardianRequest;
import careneighbors.patient.PatientRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class GuardianApiTest extends AcceptanceTest {

    @DisplayName("보호자 생성")
    @Test
    void createGuardian() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateGuardianRequest("보호자1", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .when()
                .post("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    //모든 보호자 조회
    @DisplayName("모든 보호자 조회")
    @Test
    void findAllGuardians() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateGuardianRequest("보호자1", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .when()
                .post("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());


        RestAssured
                .given().log().all()
                .when()
                .get("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }


    //특정 보호자 조회
    @DisplayName("특정 보호자 조회")
    @Test
    void findGuardiansByGuardianId() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateGuardianRequest("보호자2", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .when()
                .post("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .pathParam("guardianId", 1)

                .when()
                .get("/guardians/{guardianId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    //보호자 정보 수정
    @DisplayName("보호자 정보 수정")
    @Test
    void updateGuardian() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateGuardianRequest("보호자3", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .when()
                .post("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new UpdateGuardianRequest("보호자2", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .pathParam("guardianId", 1)
                .when()
                .put("/guardians/{guardianId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }


    //보호자 정보 수정
    @DisplayName("보호자 삭제")
    @Test
    void deleteByGuardianId() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateGuardianRequest("보호자3", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .when()
                .post("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("guardianId", 1)
                .when()
                .delete("/guardians/{guardianId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    //간병인에게 선물하기 - 간병인 목록 조회/선택?
    //@GetMapping("/findAllCaregiver")


    //간병인 조회
    @DisplayName("간병인 생성")
    @Test
    void createCaregiver() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CaregiverRequest("국적어쩌구", "콩글리쉬", "알바지옥", "비싼병원", "간병킹", "121212121212", "121212121212", "이세상어딘가", "뷁"))
                .when()
                .post("/api/caregivers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .when()
                .get("/guardians/findAllCaregiver")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

//    //간병인에게 선물하기 - 간병인 에게 선물하기
//    @PostMapping("/giftCaregiver")
//    public String giftCaregiver(@RequestBody GiftRequest giftRequest){
//        return guardianService.giftCaregiver(giftRequest);
//    }

    //간병인 조회
    @DisplayName("간병인 생성")
    @Test
    void giftCaregiver() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CaregiverRequest("국적어쩌구", "콩글리쉬", "알바지옥", "비싼병원", "간병킹", "121212121212", "121212121212", "이세상어딘가", "뷁"))
                .when()
                .post("/api/caregivers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .pathParam("caregiverId", 1)
                .when()
                .get("/api/caregivers/{caregiverId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());


        // 간병인에게 선물하기 요청
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new GiftRequest(1L, "선물아이템"))
                .when()
                .post("/guardians/gift-caregiver")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());


    }

    //보호자가 환자 아이디로 환자의 간병인 조회
    //@GetMapping("/caregiverIds/byGuardian/{guardianId}")
//보호자 환자 간병인 다 만들어야함.
    @DisplayName("보호자가 환자 아이디로 환자의 간병인 조회")
    @Test
    void findCaregiverIdsByGuardian() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CaregiverRequest("국적어쩌구", "콩글리쉬", "알바지옥", "비싼병원", "간병킹", "121212121212", "121212121212", "이세상어딘가", "뷁"))
                .when()
                .post("/api/caregivers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateGuardianRequest("보호자1", "폰번호어쩌구", "고양시어쩌구", "주민번호1234"))
                .when()
                .post("/guardians")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PatientRequest("보호자1", "남", "12121212", "31313134", "ㅇㅇ", "튼튼병원"))
                .when()
                .post("/careneighbors/patient")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .pathParam("guardianId", 1)

                .when()
                .get("/guardians/caregiverIds/byGuardian/{guardianId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());


    }
}




