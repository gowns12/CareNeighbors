package careneighbors.community.comment;


import careneighbors.community.post.Post;
import careneighbors.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PatientStatusType patientStatus;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String additionalContent;

    @Column(nullable = false)
    private String authorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guardian_id")
    private Comment guardianComment;

    @OneToMany(mappedBy = "guardianComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Comment(PatientStatusType patientStatus, String additionalContent, String authorName, Post post, Comment guardianComment, Patient patient) {
        this.patientStatus = patientStatus;
        this.additionalContent = additionalContent;
        this.authorName = authorName;
        this.post = post;
        this.guardianComment = guardianComment;
        this.patient = patient;
        this.createdAt = LocalDateTime.now();
    }

    public void update(PatientStatusType patientStatus, String additionalContent, Patient patient) {
        this.patientStatus = patientStatus;
        this.additionalContent = additionalContent;
        this.patient = patient;
        this.updatedAt = LocalDateTime.now();
    }
}