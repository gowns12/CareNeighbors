package careneighbors.community.post;

import careneighbors.community.Image.Image;
import careneighbors.community.comment.Comment;
import careneighbors.patient.Patient;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Post(String title, String content, String authorName) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isRelatedToPatient(Patient patient) {
        if (this.patient == null || patient == null) {
            return false;
        }

        // 환자 ID로 직접 비교
        if (this.patient.getId().equals(patient.getId())) {
            return true;
        }

        // 환자 이름과 주민등록번호로 비교
        if (this.patient.getName().equals(patient.getName()) &&
                this.patient.getResidentNumber().equals(patient.getResidentNumber())) {
            return true;
        }

//        // 환자가 속한 병원이 같은지 비교 (병원 정보가 있다고 가정)
//        if (this.patient.getHospital() != null && patient.getHospital() != null &&
//                this.patient.getHospital().getId().equals(patient.getHospital().getId())) {
//            return true;
//        }

        return false;
    }
}