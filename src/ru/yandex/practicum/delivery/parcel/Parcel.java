package ru.yandex.practicum.delivery.parcel;

import java.time.LocalDate;

public abstract class Parcel {

    final String description;
    final int weight;
    final String deliveryAddress;
    final LocalDate sendDay;

    public Parcel(String description, int weight, String deliveryAddress, LocalDate day) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = day;
    }

    public int calculateDeliveryCost() {
        return getBaseCost() * weight;
    }

    String getDescription() {
        return description;
    }

    int getWeight() {
        return weight;
    }

    public void packageItem() {
        System.out.printf("Посылка <<%s>> упакована\n", getDescription());
    }

    public void deliver() {
        System.out.printf("Посылка <<%s>> доставлена по адресу %s\n", description, deliveryAddress);
    }

    abstract int getBaseCost();

    @Override
    public String toString() {
        return "Parcel{" +
                "описание='" + description + '\'' +
                ", вес=" + weight +
                ", адрес доставки='" + deliveryAddress + '\'' +
                ", дата отправки='" + sendDay + '\'' +
                '}';
    }
}
