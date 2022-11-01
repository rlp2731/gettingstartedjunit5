package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeConverter should")
class DateTimeConverterTest {

    @Nested
    @DisplayName("convert string with 'today' keyword")
    class TodayTests {
    @Test
    @DisplayName("correctly")
    void convertTodayStringCorrectly(){
        LocalDate today = LocalDate.of(2022,11,1);
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                today);
        assertEquals(result, LocalDateTime.of(2022,11,1,13,0),
                () -> "Failed to convert 'today' string to expected date time, today passed was: " +today);
    }
    @Test
    @DisplayName("correctly regardless of case ")
    void convertTodayStringCorrectlyCaseInsensitive(){
        LocalDate today = LocalDate.of(2022,11,1);
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm",
                today);
        assertEquals(result, LocalDateTime.of(2022,11,1,13,0),
                () -> "Failed to convert 'today' string to expected date time, today passed was: " +today);
        }
    }

    @Test
    @DisplayName("convert expected date and time pattern in string correctly")
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                LocalDate.of(2022,11,1));
        assertEquals(result, LocalDateTime.of(2022,11,1,13,0));
    }

    @Test
    @DisplayName("throw exception if entered pattern of string incorrectly")
    void throwExceptionIfIncorrectPatternProvided(){
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("11/1/2022 100 pm",
                LocalDate.of(2022, 11, 1)));
        assertEquals("Unable to create date time from: [11/1/2022 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a], Text '11/1/2022 100 PM' " +
                "could not be parsed at index 13", error.getMessage());
    }
}