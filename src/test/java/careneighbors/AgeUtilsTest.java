package careneighbors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AgeUtilsTest {
    @Test
    void testCalculateKoreanAge_validResidentNumber_20thCentury() {
        // given
        String residentNumber = "990101-1234567"; // 1999년 1월 1일 출생

        // when
        int age = AgeUtils.calculateKoreanAge(residentNumber);

        // then
        assertEquals(LocalDate.now().getYear() - 1999, age, "만나이 계산이 올바르지 않습니다.");
    }

    @Test
    void testCalculateKoreanAge_validResidentNumber_21stCentury() {
        // given
        String residentNumber = "010101-3456789"; // 2001년 1월 1일 출생

        // when
        int age = AgeUtils.calculateKoreanAge(residentNumber);

        // then
        assertEquals(LocalDate.now().getYear() - 2001, age, "만나이 계산이 올바르지 않습니다.");
    }

    @Test
    void testCalculateKoreanAge_invalidResidentNumber() {
        // given
        String residentNumber = "9901"; // 잘못된 주민번호

        // when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AgeUtils.calculateKoreanAge(residentNumber);
        });

        assertEquals("유효하지 않은 주민번호입니다.", exception.getMessage());
    }

    @Test
    void testCalculateAge_directDate() {
        // given
        LocalDate birthDate = LocalDate.of(1995, 5, 10); // 1995년 5월 10일
        LocalDate currentDate = LocalDate.of(2023, 5, 9); // 2023년 5월 9일 기준

        // when
        int age = AgeUtils.calculateAge(birthDate, currentDate);

        // then
        assertEquals(27, age, "만나이 계산이 올바르지 않습니다.");
    }

    @Test
    void testCalculateAge_sameBirthday() {
        // given
        LocalDate birthDate = LocalDate.of(1995, 5, 10); // 1995년 5월 10일
        LocalDate currentDate = LocalDate.of(2023, 5, 10); // 2023년 5월 10일 기준

        // when
        int age = AgeUtils.calculateAge(birthDate, currentDate);

        // then
        assertEquals(28, age, "만나이 계산이 올바르지 않습니다.");
    }

    @Test
    void testCalculateForeignAge() {
        // 테스트 케이스 1: 1999년 2월 13일생
        String foreignerNumber1 = "990213-1234567";
        int age1 = AgeUtils.calculateForeignAge(foreignerNumber1);
        // 오늘 날짜가 2025년 1월 22일이면, 1999년 2월 13일생은 25세
        assertEquals(25, age1);

        // 테스트 케이스 2: 2005년 12월 25일생
        String foreignerNumber2 = "051225-7654321";
        int age2 = AgeUtils.calculateForeignAge(foreignerNumber2);
        // 오늘 날짜가 2025년 1월 22일이면, 2005년 12월 25일생은 19세
        assertEquals(19, age2);

        // 테스트 케이스 3: 2010년 6월 15일생
        String foreignerNumber3 = "100615-9876543";
        int age3 = AgeUtils.calculateForeignAge(foreignerNumber3);
        // 오늘 날짜가 2025년 1월 22일이면, 2010년 6월 15일생은 14세
        assertEquals(14, age3);
    }
}
