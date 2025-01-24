package careneighbors.Hospital;

import careneighbors.AcceptanceTest;
import careneighbors.hospital.hospitalDto.HospitalRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HospitalTest extends AcceptanceTest {
    @DisplayName("병원을 생성한다")
    @Test
    void createHospitalTest() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest("nn","","","","",1,"",""))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(201);

    }
}
