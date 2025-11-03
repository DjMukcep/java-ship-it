package ru.yandex.practicum.delivery.parcel;

import java.time.LocalDate;

public abstract class Parcel {

    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected LocalDate sendDay;

    public Parcel(String description, int weight, String deliveryAddress, LocalDate day) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = day;
    }

    public int calculateDeliveryCost() {
        return getBaseCost() * weight;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public void packageItem() {
        System.out.printf("Посылка <<%s>> упакована\n", getDescription());
    }

    public void deliver() {
        System.out.printf("Посылка <<%s>> доставлена по адресу %s\n", description, deliveryAddress);
    }

    protected abstract int getBaseCost();

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
