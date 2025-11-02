package ru.yandex.practicum.delivery.parcel;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class ParcelBox<T extends Parcel> {

    private final ArrayList<T> parcels;
    private final int maxWeight;
    private static final Logger logger = Logger.getLogger(ParcelBox.class.getName());

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
    }

    public void addParcel(T parcel) {
        int overallWeight = parcels.stream().mapToInt(Parcel::getWeight).sum() + parcel.getWeight();
        if (maxWeight < overallWeight) {
            logger.warning("Отклонено: максимальный вес превышен.");
            return;
        }
        parcels.add(parcel);
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }
}
