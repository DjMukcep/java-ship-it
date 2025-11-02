package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.ParcelBox;
import ru.yandex.practicum.delivery.parcel.StandardParcel;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class AddParcelTest {

    private static ParcelBox<StandardParcel> box;
    private static StandardParcel parcel1;
    private static StandardParcel parcel2;
    private static StandardParcel parcel3;

    @BeforeAll
    static void setUp() {
        box = new ParcelBox<>(10);
        parcel1 = new StandardParcel(
                "parcel1",
                6,
                "address",
                LocalDate.now()
        );
        parcel2 = new StandardParcel(
                "parcel2",
                4,
                "address",
                LocalDate.now()
        );
        parcel3 = new StandardParcel(
                "parcel3",
                1,
                "address",
                LocalDate.now()
        );
    }

    @BeforeEach
    void beforeEach() {
        box.getAllParcels().clear();
    }

    @Test
    void shouldAddParcel() {
        box.addParcel(parcel1);
        assertEquals(1,box.getAllParcels().size());
    }

    @Test
    void shouldAddParcelsWhenMaxWeightNotExceeded() {
        box.addParcel(parcel1);
        box.addParcel(parcel2);
        assertEquals(2,box.getAllParcels().size());
    }

    @Test
    void shouldNotAddParcelWhenMaxWeightExceeded() {
        box.addParcel(parcel1);
        box.addParcel(parcel2);
        assertEquals(2,box.getAllParcels().size());

        box.addParcel(parcel3);
        assertEquals(2,box.getAllParcels().size());
    }
}
