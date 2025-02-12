package careneighbors.Hospital;

import careneighbors.AcceptanceTest;
import careneighbors.hospital.Hospital;
import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class HospitalTest extends AcceptanceTest {

    HospitalResponse hospitalResponse = new HospitalResponse(
            1L,
            "aa",
            "1221",
            "1212",
            "222",
            "d2da",
            12212,
            "22",
            "2222"
    );
    @DisplayName("병원을 생성한다")
    @Test
    void createHospitalTest() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest(1L,"nn","","","","",1,"",""))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(201);
    }

    @DisplayName("병원을 조회한다")
    @Test
    void getHospitalTest() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(hospitalResponse)
                .when()
                .get("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
    @DisplayName("id로 병원 조회")
    @Test
    void getHospitalByIdTest(){
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(hospitalResponse)
                .pathParam("id",1)
                .when()
                .get("/api/hospitals/{id}")
                .then().log().all()
                .statusCode(200);
    }
    @DisplayName("병원을 삭제한다")
    @Test
    void deletHospitalTest(){
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest(1L,"명지병원", "화정",
                        "22552", "2252", "world", 2225,
                        "http://", "221412241421241214124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(201);

        RestAssured

                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id" , 1)
                .when()
                .delete("/api/hospitals/{id}")
                .then().log().all()
                .statusCode(204);
    }
}
