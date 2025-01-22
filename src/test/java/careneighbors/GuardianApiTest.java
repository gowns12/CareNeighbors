package careneighbors;


import careneighbors.guardian.CreateGuardianRequest;
import careneighbors.guardian.UpdateGuardianRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

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
}