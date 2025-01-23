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
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String companyName;

    @NonNull
    @Column(nullable = false)
    private String address;

    @NonNull
    @Column(nullable = false)
    private String contactNumber;

    @NonNull
    @Column(nullable = false)
    private String registrationNumber;

    @NonNull
    @Column(nullable = false)
    private String type;

    @NonNull
    @Column(nullable = false)
    private Integer bedCount;

    @Column(nullable = true)
    private String website;

    @Column(nullable = true)
    private String imageUrl;

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




