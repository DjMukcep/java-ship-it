package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

public class IsExpiredTest {

    private static PerishableParcel parcel;

    @BeforeAll
    static void setUp() {
        parcel = new PerishableParcel(
                "груши",
                5,
                "Москва",
                LocalDate.of(2025, Month.NOVEMBER,6),
                Duration.ofDays(7)
        );
    }

    @Test
    void shouldReturnTrueIfDateExpired() {
        boolean isExpired = parcel.isExpired(LocalDate.of(2025,Month.NOVEMBER,14));
        assertTrue(isExpired);
    }

    @Test
    void shouldReturnFalseIfDateOnBoundOfExpiration() {
        boolean isExpired = parcel.isExpired(LocalDate.of(2025,Month.NOVEMBER,13));
        assertFalse(isExpired);
    }

    @Test
    void shouldReturnFalseIfDateInBoundOfExpiration() {
        boolean isExpired = parcel.isExpired(LocalDate.of(2025,Month.NOVEMBER,10));
        assertFalse(isExpired);
    }
}
