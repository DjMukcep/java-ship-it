package ru.yandex.practicum.delivery.parcel;

import java.time.Duration;
import java.time.LocalDate;

public final class PerishableParcel extends Parcel {

    private static final int BASE_COST = 3;
    private final Duration timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, LocalDate day, Duration timeToLive) {
        super(description, weight, deliveryAddress, day);
        this.timeToLive = timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(LocalDate currentDay) {
        return currentDay.isAfter(sendDay.plusDays(timeToLive.toDays()));
    }

    @Override
    public String toString() {
        return "PerishableParcel{" +
                "описание='" + description + '\'' +
                ", вес=" + weight +
                ", адрес доставки='" + deliveryAddress + '\'' +
                ", дата отправки='" + sendDay + '\'' +
                ", срок годности (дней)=" + timeToLive.toDays() +
                '}';
    }
}
