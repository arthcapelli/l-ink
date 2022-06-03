package br.com.tcc.link.fixture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class LocalDateTimeFixture {

    public static LocalDateTime japan2011Earthquake() {
        return LocalDateTime.of(2011, Month.MARCH.getValue(), 11, 14, 46);
    }

    public static LocalDateTime usa2001TwinTowersAttack() {
        return LocalDateTime.of(2001, Month.SEPTEMBER.getValue(), 11, 8, 46);
    }

    public static LocalDate unixMillennialBug() {
        return LocalDate.of(2000, 1, 1);
    }

}
