package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    private static StandardParcel standardParcel;
    private static PerishableParcel perishableParcel;
    private static FragileParcel fragileParcel;

    @BeforeAll
    static void setUp() {
        standardParcel = new StandardParcel(
                "стул",
                5,
                "Москва",
                LocalDate.of(2025, Month.NOVEMBER,4)
        );
        perishableParcel = new PerishableParcel(
                "груши",
                3,
                "куда-то туда",
                LocalDate.of(2025,Month.NOVEMBER,16),
                Duration.ofDays(7)
        );
        fragileParcel = new FragileParcel(
                "посуда",
                6,
                "на юг",
                LocalDate.of(2025,Month.NOVEMBER,23)
        );
    }

    @Test
    void shouldReturnCorrectCostOfStandardParcel() {
        int cost = standardParcel.calculateDeliveryCost();
        assertEquals(10,cost);
    }

    @Test
    void shouldReturnCorrectCostOfFragileParcel() {
        int cost = fragileParcel.calculateDeliveryCost();
        assertEquals(24,cost);
    }

    @Test
    void shouldReturnCorrectCostOfPerishableParcel() {
        int cost = perishableParcel.calculateDeliveryCost();
        assertEquals(9,cost);
    }
}
