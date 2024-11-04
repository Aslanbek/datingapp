package kz.astana.dating.app.utils;

import jakarta.servlet.jsp.jstl.fmt.LocalizationContext;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class DateTimeUtils {
    public static int getAge(LocalDate birthDate) {
        return Math.toIntExact(ChronoUnit.YEARS.between(birthDate, LocalDate.now()));
    }
}
