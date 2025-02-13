//package careneighbors.education;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EducationService {
//    private final EducationContentRepository educationContentRepository;
//    private final CaregiverEducationProgressRepository progressRepository;
//
//    public List<EducationContent> getAvailableContents(Caregiver caregiver) {
//        // 간병인의 언어 수준, 경력 등을 고려하여 적절한 교육 콘텐츠 목록 반환
//    }
//
//    public void markContentAsCompleted(Caregiver caregiver, Long contentId) {
//        // 교육 콘텐츠 완료 처리
//    }
//
//    public void updateVideoProgress(Caregiver caregiver, Long contentId, Integer watchedSeconds) {
//        // 동영상 시청 진행 상황 업데이트
//    }
//
//    public Integer submitQuizAnswers(Caregiver caregiver, Long quizId, List<Integer> answers) {
//        // 퀴즈 답변 제출 및 점수 계산
//    }
//
//    // 기타 필요한 메소드들...
//}
