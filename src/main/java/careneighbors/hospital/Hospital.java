package careneighbors.hospital;



import careneighbors.caregiver.Caregiver;
import careneighbors.guardian.Guardian;
import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.patient.PatientHospital;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String companyName;//todo 병원명

    @NonNull
    @Column(nullable = false)
    private String address;//Todo 병원 주소

    @NonNull
    @Column(nullable = false)
    private String contactNumber;//Todo 병원 번호

    @NonNull
    @Column(nullable = false)
    private String registrationNumber;//Todo 병원등록번호

    @NonNull
    @Column(nullable = false)
    private String type; //Todo 병원 사립 공립 유형

    @NonNull
    @Column(nullable = false)
    private Integer bedCount; //Todo 병상 수

    @Column(nullable = true)
    private String website;//Todo 병원 홈페이지

    @Column(nullable = true)
    private String imageUrl; //Todo 병원 이미지

    @Column(nullable = true)
    private Integer hospitalBill; //Todo 병원비 청구,내역

    @OneToMany(mappedBy = "hospital")
    private List<PatientHospital> patientsHospitals; //Todo 병원환자 테이블 관계설정


    @OneToMany(mappedBy = "hospital")
    private List<Caregiver> caregivers; // todo 병원 간병인 관계설정

    //Todo 병원비 내역을 저장하는 필드
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<HospitalBill> hospitalBills;


    public void addHospitalBill(HospitalBill bill) {
        this.hospitalBills.add(bill);
        bill.setHospital(this); // 병원과 병원비 연결
    }

    public Hospital(@NonNull String companyName,
                    @NonNull String address,
                    @NonNull String contactNumber,
                    @NonNull String registrationNumber,
                    @NonNull String type,
                    @NonNull Integer bedCount,
                    String website,
                    String imageUrl) {

        this.companyName = companyName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.bedCount = bedCount;
        this.website = website;
        this.imageUrl = imageUrl;
    }

    public void update(String companyName,
                       String address,
                       String contactNumber,
                       Integer bedCount,
                       String website,
                       String imageUrl) {

        this.companyName = companyName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.bedCount = bedCount;
        this.website = website;
        this.imageUrl = imageUrl;
    }
}




