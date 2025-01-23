package careneighbors;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeUtils {


    // 주민번호에서 생년월일 추출 후 만나이 계산
    public static int calculateKoreanAge(String residentNumber) {
        // 주민번호에서 생년월일 추출
        String birthDateString = extractBirthDate(residentNumber);

        // LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        // 현재 날짜
        LocalDate now = LocalDate.now();

        // Period를 사용해 만나이 계산
        return calculateAge(birthDate, now);
    }

    public static int calculateForeignAge(String foreignerNumber) {
        // 외국인등록번호에서 생년월일 추출 (앞 6자리)
        String birthDateString = foreignerNumber.substring(0, 6);  // 예시: 990213

        // 앞 2자리로 1900년대와 2000년대 구분하기
        String yearPrefix = birthDateString.substring(0, 2);  // 예시: 99
        // 현재 날짜
        LocalDate now = LocalDate.now();
        int nowYear = Integer.parseInt(String.valueOf(now.getYear()).substring(2));

        int fullYear = Integer.parseInt(yearPrefix) < nowYear ? 20 : 19; // 2000년대는 22 미만으로 가정

        // 생년월일을 LocalDate로 변환 (앞 2자리로 년도 선택)
        String fullBirthDateString = fullYear + birthDateString;  // 예시: 19990213

        // 생년월일을 LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(fullBirthDateString, formatter);

        // Period를 사용해 만나이 계산
        return calculateAge(birthDate, now);
    }

    // 생년월일에서 만나이 계산하는 로직
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate != null && currentDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        }
        throw new IllegalArgumentException("유효하지 않은 생년월일입니다.");
    }

    // 주민번호에서 생년월일 추출
    private static String extractBirthDate(String residentNumber) {
        if (residentNumber == null || residentNumber.length() < 6) {
            throw new IllegalArgumentException("유효하지 않은 주민번호입니다.");
        }

        char centuryCode = residentNumber.charAt(7); // 7번째 숫자로 세기 구분
        String birthYearPrefix = switch (centuryCode) {
            case '1', '2' -> // 1900년대
                    "19";
            case '3', '4' -> // 2000년대
                    "20";
            default -> throw new IllegalArgumentException("유효하지 않은 주민번호입니다.");
        };

        return birthYearPrefix + residentNumber.substring(0, 6);
    }
}


