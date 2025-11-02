package ru.yandex.practicum.delivery.parcel;

import java.time.LocalDate;

public final class FragileParcel extends Parcel implements Trackable{

    private static final int BASE_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, LocalDate day) {
        super(description, weight, deliveryAddress, day);
    }

    @Override
    int getBaseCost() {
        return BASE_COST;
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка <<%s>> обёрнута в защитную плёнку\n",getDescription());
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка <<%s>> изменила местоположение на %s\n",description,newLocation);
    }
}
