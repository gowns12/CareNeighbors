package careneighbors.hospital;

import careneighbors.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HospitalBill {

    //Todo 병원비 지출

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Double treatmentCost; // 치료비 발생 비용

    @Column(nullable = true)
    private Double roomCharge; // 방 (병상) 이용 비용

    @Column(nullable = true)
    private Double careCost; // 간병후 발생 비용

    @Column(nullable = false)
    private Double totalCost; // 총 발생 비용

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public HospitalBill(
            Hospital hospital,
            Patient patient,
            Double careCost,
            Double roomCharge,
            Double treatmentCost)
    {
        this.hospital = hospital;
        this.patient = patient;
        this.careCost = careCost;
        this.roomCharge = roomCharge;
        this.treatmentCost = treatmentCost;
        this.totalCost = careCost + treatmentCost + roomCharge;
    }

    public HospitalBill(Hospital hospital, Patient patient, Double totalCost) {
        this.hospital = hospital;
        this.patient = patient;
        this.totalCost = totalCost;

    }
}
