package careneighbors.hospital;

import careneighbors.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HospitalBill {

    //Todo 병원비 지출

    @Id
    @EqualsAndHashCode.Include
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
    private Double careCost; // 간병후 발생 비용

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Double totalCost; // 총 발생 비용


    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "hospital_id")
    @Setter(AccessLevel.PUBLIC)
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Patient patient;

    public HospitalBill(Patient patient,  Double careCost, Double roomCharge, Double treatmentCost) {
        this.patient = patient;
        this.careCost = careCost;
        this.roomCharge = roomCharge;
        this.treatmentCost = treatmentCost;
        this.totalCost = careCost + treatmentCost + roomCharge;
    }
}
