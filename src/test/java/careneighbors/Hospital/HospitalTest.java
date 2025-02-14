package careneighbors.Hospital;

import careneighbors.AcceptanceTest;
import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import careneighbors.patient.Gender;
import careneighbors.patient.PatientHospitalRequest;
import careneighbors.patient.PatientRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class HospitalTest extends AcceptanceTest {

    @DisplayName("병원을 생성한다")
    @Test
    void createHospitalTest() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest("nn", "", "", "", "", 1, "", ""))
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
                .body(new HospitalRequest("nn", "", "", "", "", 1, "", ""))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(201);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("id로 병원 조회")
    @Test
    void getHospitalByIdTest() {


        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest("nn", "", "", "", "", 1, "", ""))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(201);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .get("/api/hospitals/{id}")
                .then().log().all()
                .statusCode(200);
    }

    @DisplayName("병원을 삭제한다")
    @Test
    void deletHospitalTest() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest(
                        "명지병원",
                        "화정",
                        "22552",
                        "2252",
                        "world", 2225,
                        "http://",
                        "221412241421241214124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(201);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .delete("/api/hospitals/{id}")
                .then().log().all()
                .statusCode(204);
    }

    @DisplayName("병원 수정")
    @Test
    void putHospitalTest() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest( "명지병원", "화정",
                        "22552", "2252", "world", 2225,
                        "http://", "221412241421241214124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest( "명지병원", "화2",
                        "22552", "2252", "worl22d", 2225,
                        "http://", "22141224142114124"))
                .pathParam("id", 1)
                .when()
                .put("/api/hospitals/{id}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("환자 Id 로 조회")
    @Test
    void getPatientId() {
        //Todo 병원 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest( "명지병원", "화2",
                        "22552", "2252", "worl22d", 2225,
                        "http://", "22141224142114124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //TOdo 환자 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PatientHospitalRequest( "김환자", "남성", "2222", "01099993333", "경상",
                        "명지병원"))
                .pathParam("id", 1)
                .when()
                .post("/api/hospitals/{id}/patient")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //TOdo 환자 조회
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("hospitalId", 1)
                .pathParam("patientId", 1)
                .when()
                .get("/api/hospitals/patient/{hospitalId}/{patientId}")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("환자 이름으로 조회")
    @Test
    void getPatientList() {

        //Todo 병원 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest(
                        "명지병원",
                        "화2",
                        "22552",
                        "2252",
                        "worl22d",
                        2225,
                        "http://",
                        "22141224142114124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //TOdo 환자 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PatientRequest("김환자",
                        "남성",
                        "2222",
                        "01099993333",
                        "경상",
                        "명지병원"))
                .when()
                .post("/careneighbors/patient")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
        //Todo 원래는 Created 가 맞지만 api 설계중에 HttpStatues 를 ok 로만들어둬서 201 이 아니라 200 이 반환되는 오류가 발생됨


        //Todo 환자 이름을 조회
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("patient", "명지병원")
                .when()
                .get("/api/hospitals/patient")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("환자 병원비 내역 생성")
    @Test
    void generateHospitalBill() {
        //Todo 병원 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest(
                        "명지병원",
                        "화2",
                        "22552",
                        "2252",
                        "worl22d",
                        2225,
                        "http://",
                        "22141224142114124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //Todo 환자 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PatientRequest(
                         "김환자"
                        , "남성"
                        , "2222"
                        , "01099993333"
                        , "경상",
                        "명지병원"))
                .when()
                .post("/careneighbors/patient")
                .then().log().all()
                .statusCode(200);

        //Todo 병원비 계산
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("hospitalId", 1)
                .pathParam("patientId", 1)
                .queryParam("treatmentCost", 500000)
                .queryParam("roomCharge", 500001.2)
                .queryParam("careCost", 5000000)
                .when()
                .post("/api/hospitals/{hospitalId}/{patientId}/bills")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    //TOdo 병원비 내역 조회
    @Test
    void getHospitalBill() {

        //Todo 병원 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new HospitalRequest(
                        "명지병원",
                        "화2",
                        "22552",
                        "2252",
                        "world22d",
                        2225,
                        "http://",
                        "22141224142114124"))
                .when()
                .post("/api/hospitals")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //Todo 환자 등록
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new PatientRequest(
                        "김환자"
                        , "남성"
                        , "2222"
                        , "01099993333"
                        , "경상",
                        "명지병원"))
                .when()
                .post("/careneighbors/patient")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        //Todo 병원비 계산
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("hospitalId", 1)
                .pathParam("patientId", 1)
                .queryParam("treatmentCost", 500000)
                .queryParam("roomCharge", 500001.2)
                .queryParam("careCost", 5000000)
                .when()
                .post("/api/hospitals/{hospitalId}/{patientId}/bills")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //Todo 병원비 내역 조회
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("hospitalId", 1)
                .pathParam("patientId", 1)
                .when()
                .get("/api/hospitals/{hospitalId}/{patientId}/bills")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
