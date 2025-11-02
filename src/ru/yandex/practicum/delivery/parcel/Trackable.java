package ru.yandex.practicum.delivery.parcel;

public interface Trackable {
    void reportStatus(String newLocation);
}
