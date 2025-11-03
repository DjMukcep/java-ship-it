package ru.yandex.practicum.delivery.parcel;

import java.time.LocalDate;

public final class StandardParcel extends Parcel {

    private static final int BASE_COST = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, LocalDate day) {
        super(description, weight, deliveryAddress, day);
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}
