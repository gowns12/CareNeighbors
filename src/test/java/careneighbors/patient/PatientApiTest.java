package careneighbors.patient;

import careneighbors.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PatientApiTest extends AcceptanceTest {

    @DisplayName("환자 생성")
    @Test
    void createPatient() {
        RestAssured.given().log().all().contentType(ContentType.JSON).body(new PatientRequest("환자이름", "남성", "460513-1236456", "010-1234-1234", "관절염, 욕창, 치매, 폐렴", "국립암센터")).when().post("/careneighbors/patient").then().log().all().statusCode(200);
    }

    @DisplayName("환자목록 읽기")
    @Test
    void readAllPatient() {
        RestAssured.given().log().all().contentType(ContentType.JSON).body(new PatientRequest("환자이름", "남성", "460513-1236456", "010-1234-1234", "관절염, 욕창, 치매, 폐렴", "국립암센터")).when().post("/careneighbors/patient").then().log().all().statusCode(HttpStatus.OK.value());

        RestAssured.given().log().all().when().get("careneighbors/patient").then().log().all().statusCode(200).extract().as(List.class);
    }

    @DisplayName("특정 환자 읽기")
    @Test
    void readPatientByNumber() {
        RestAssured.given().log().all().contentType(ContentType.JSON).body(new PatientRequest("환자이름", "남성", "460513-1236456", "010-1234-1234", "관절염, 욕창, 치매, 폐렴", "국립암센터")).when().post("/careneighbors/patient").then().log().all().statusCode(HttpStatus.OK.value());

        PatientResponse rp = RestAssured
                .given().log().all()
                .pathParam("patientId", "1")
                .when()
                .get("careneighbors/patient/{patientId}")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(PatientResponse.class);

        System.out.println(rp);
    }

    @DisplayName("환자 정보 수정")
    @Test
    void updatePatient() {
        RestAssured.given().contentType(ContentType.JSON).body(new PatientRequest("환자이름", "남성", "460513-1236456", "010-1234-1234", "관절염, 욕창, 치매, 폐렴", "국립암센터")).when().post("/careneighbors/patient").then();

        PatientResponse rp1 = RestAssured
                .given()
                .pathParam("patientId", "1")
                .when()
                .get("careneighbors/patient/{patientId}")
                .then()
                .statusCode(200)
                .extract()
                .as(PatientResponse.class);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PatientRequest("환자이름", "여성", "460513-2236456", "010-1234-1234", "관절염, 욕창, 치매, 폐렴", "국립암센터"))
                .pathParam("patientId","1")
                .when()
                .put("careneighbors/patient/{patientId}")
                .then().log().all()
                .statusCode(200);

        PatientResponse rp2 = RestAssured
                .given()
                .pathParam("patientId", "1")
                .when()
                .get("careneighbors/patient/{patientId}")
                .then()
                .statusCode(200)
                .extract()
                .as(PatientResponse.class);

        System.out.println(rp1);
        System.out.println(rp2);
    }

    @Test
    void deletePatient() {
        RestAssured.given().contentType(ContentType.JSON).body(new PatientRequest("환자이름", "남성", "460513-1236456", "010-1234-1234", "관절염, 욕창, 치매, 폐렴", "국립암센터")).when().post("/careneighbors/patient").then();
        RestAssured
                .given()
                .pathParam("patientId", "1")
                .when()
                .get("careneighbors/patient/{patientId}")
                .then()
                .statusCode(200)
                .extract()
                .as(PatientResponse.class);
        RestAssured
                .given().log().all()
                .pathParam("patientId","1")
                .when()
                .delete("careneighbors/patient/{patientId}")
                .then().log().all()
                .statusCode(200);
        RestAssured
                .given()
                .pathParam("patientId", "1")
                .when()
                .get("careneighbors/patient/{patientId}")
                .then()
                .statusCode(500);
    }
}
