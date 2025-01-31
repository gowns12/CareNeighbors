package careneighbors.community.comment;


public enum PatientStatusType {
    STABLE("안정적"),
    IMPROVING("호전 중"),
    DETERIORATING("악화 중"),
    CRITICAL("위험"),
    PAIN("통증 호소"),
    SLEEPING("수면 중"),
    EATING("식사 중"),
    MEDICATION_TAKEN("약물 복용 완료"),
    THERAPY_COMPLETED("치료 완료"),
    VITAL_SIGNS_CHECKED("활력징후 확인");

    private final String description;

    PatientStatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}