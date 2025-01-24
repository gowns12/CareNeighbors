package careneighbors.hospital;

import careneighbors.guardian.Guardian;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HospitalBill {

    //Todo 병원비 지출

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = true)
    @Setter(AccessLevel.NONE)
    private Double treatmentCost; // 치료비 발생 비용

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Double roomCharge; // 방 (병상) 이용 비용

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Double CareCost; // 간병후 발생 비용

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Double totalCost; // 총 발생 비용

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @Setter(AccessLevel.PUBLIC)
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "guardian_id")
    @Setter(AccessLevel.NONE)
    private Guardian guardian;

    public HospitalBill(Guardian guardian,  Double careCost, Double roomCharge, Double treatmentCost) {
        this.guardian = guardian;
        this.CareCost = careCost;
        this.roomCharge = roomCharge;
        this.treatmentCost = treatmentCost;
        this.totalCost = careCost + treatmentCost + roomCharge;
    }
}
