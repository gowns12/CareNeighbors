package careneighbors.hospital;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private Integer registrationNumber;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer bedCount;

    @Column(nullable = true)
    private String website;

    @Column(nullable = true)
    private String imageUrl;

    public Hospital(String companyName, String address, String contactNumber, Integer registrationNumber, String type, Integer bedCount, String website, String imageUrl) {
        this.companyName = companyName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.bedCount = bedCount;
        this.website = website;
        this.imageUrl = imageUrl;
    }

    public void update(HospitalRequest rq) {
        companyName = rq.companyName();
        address = rq.address();
        contactNumber = rq.contactNumber();
        registrationNumber = rq.registrationNumber();
        type = rq.type();
        bedCount = rq.bedCount();
        website = rq.website();
        imageUrl = rq.imageUrl();
    }
}



