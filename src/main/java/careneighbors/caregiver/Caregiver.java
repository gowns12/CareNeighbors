package careneighbors.caregiver;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String affiliation;

    @Column(nullable = false)
    private String workPlace;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String imageUrl;

    private Boolean isBlackList = false;

    public Caregiver(String nationality, String language, String affiliation, String workPlace, String name, String registrationNumber, String contactNumber, String address, String imageUrl) {
        this.nationality = nationality;
        this.language = language;
        this.affiliation = affiliation;
        this.workPlace = workPlace;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.contactNumber = contactNumber;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public void update(CaregiverRequest rq) {
        nationality = rq.nationality();
        language = rq.language();
        affiliation = rq.affiliation();
        workPlace = rq.workPlace();
        name = rq.name();
        registrationNumber = rq.registrationNumber();
        contactNumber = rq.contactNumber();
        address = rq.address();
        imageUrl = rq.imageUrl();
    }
}

//    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
//    private List<Certification> certifications;
//
//    @ElementCollection
//    @CollectionTable(name = "caregiver_specializations")
//    private List<String> specializations;
//
//    @Column(nullable = false)
//    private String experience;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private CaregiverGrade grade;

//    @ManyToOne
//    @JoinColumn(name = "guardian")
//    private List<Guardian> guardian;
//
//    @OneToMany(mappedBy = "patient")
//    private List<Patient> patient;
//
//}
//
//@Entity
//public class Certification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private LocalDate issueDate;
//
//    @Column(nullable = false)
//    private LocalDate expiryDate;
//
//    @ManyToOne
//    @JoinColumn(name = "caregiver_id")
//    private Caregiver caregiver;
//}
//


//
//    public class CaregivingLog {
//        private Long id;
//        private LocalDateTime dateTime;
//        private String patientName;
//        private String caregiverName;
//
//        // 건강 상태 기록
//        private MealInfo mealInfo;
//        private List<Medication> medications;
//        private ExcretionInfo excretionInfo;
//        private VitalSigns vitalSigns;
//
//        // 간병 활동 내역
//        private List<PositionChange> positionChanges;
//        private HygieneInfo hygieneInfo;
//        private ExerciseInfo exerciseInfo;
//        private WalkInfo walkInfo;
//
//        // 특이사항
//        private String moodOrConditionChanges;
//        private String painComplaints;
//        private SleepInfo sleepInfo;
//        private String emergencyResponse;
//
//        // 의료진 방문 기록
//        private List<MedicalVisit> medicalVisits;
//
//        // 보호자 전달사항
//        private String guardianNotes;
//
//        // 내부 클래스들
//        public static class MealInfo {
//            private List<Meal> meals;
//            private double fluidIntake;
//        }
//
//        public static class Meal {
//            private LocalDateTime time;
//            private String description;
//            private double amount;
//            //식사 시간과 섭취량
//        }
//
//        public static class Medication {
//            private String name;
//            private String dosage;
//            private LocalDateTime time;
//            //약물 종류, 용량, 투약 시간
//        }
//
//        public static class ExcretionInfo {
//            private int urineCount;
//            private int bowelMovementCount;
//            private String condition;
//            //대소변 횟수, 양, 상태
//        }
//
//        public static class VitalSigns {
//            private double temperature;
//            private int bloodPressureSystolic;
//            private int bloodPressureDiastolic;
//            private int heartRate;
//            private Double bloodSugar;
//            //체온, 혈압, 맥박, 혈당 측정값
//        }
//
//        public static class PositionChange {
//            private LocalDateTime time;
//            private String position;
//            //체위변경 횟수와 시간
//        }
//
//        public static class HygieneInfo {
//            private boolean faceWashed;
//            private boolean bathed;
//            private boolean oralCare;
//            //개인위생 관리 (세면, 목욕, 구강관리 등
//        }
//
//        public static class ExerciseInfo {
//            private String exerciseType;
//            private int duration;
//        }
//
//        public static class WalkInfo {
//            private boolean walked;
//            private LocalDateTime time;
//            private int duration;
//            //운동 및 재활 활동
//        }
//
//        public static class SleepInfo {
//            private LocalDateTime sleepTime;
//            private LocalDateTime wakeTime;
//            private String quality;
//            //수면 상태 및 시간
//        }
//
//        public static class MedicalVisit {
//            private String visitorRole;
//            private LocalDateTime visitTime;
//            private String notes;
//
//            //간호사 방문 및 처치 내용
//        }
//
//
//    }

