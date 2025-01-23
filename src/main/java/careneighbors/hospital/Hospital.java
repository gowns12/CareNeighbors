package careneighbors.hospital;



import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Todo Id

    @NonNull
    @Column(nullable = false)
    private String companyName; //Todo 병원명

    @NonNull
    @Column(nullable = false)
    private String address; //Todo 병원 주소

    @NonNull
    @Column(nullable = false)
    private String contactNumber;//Todo 병원 연락쳐

    @NonNull
    @Column(nullable = false)
    private String registrationNumber;//Todo 사업자 등록번호

    @NonNull
    @Column(nullable = false)
    private String type; // Todo 병원의 사립 공립

    @NonNull
    @Column(nullable = false)
    private Integer bedCount; //Todo 병상수

    @Column(nullable = true)
    private String website; //Todo 병원 홈페이지

    @Column(nullable = true)
    private String imageUrl;// TOdo 병원 이미지


    public Hospital(@NonNull String companyName, @NonNull String address, @NonNull String contactNumber, @NonNull String registrationNumber, @NonNull String type, @NonNull Integer bedCount, String website) {
        this.companyName = companyName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.bedCount = bedCount;
        this.website = website;
    }

    public void update(HospitalRequest rq) {
        companyName = rq.companyName();
        address = rq.address();
        contactNumber = rq.contactNumber();
        bedCount = rq.bedCount();
        website = rq.website();
        imageUrl = rq.imageUrl();
    }
}




