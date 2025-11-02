package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.parcel.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackables = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(20);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(25);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(15);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить местоположение посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.print("""
                Выберите тип посылки:
                1 — стандартная
                2 — скоропортящаяся
                3 — хрупкая
                """);
        int parcelType = Integer.parseInt(scanner.nextLine());
        processUserInput(parcelType);
    }

    private static void sendParcels() {
        allParcels.forEach(parcel -> {
            parcel.packageItem();
            parcel.deliver();
        });
    }

    private static void calculateCosts() {
        int overallCost = allParcels.stream().mapToInt(Parcel::calculateDeliveryCost).sum();
        System.out.printf("Общая стоимость: %d\n", overallCost);
    }

    private static void reportStatus() {
        if (trackables.isEmpty()) {
            System.out.println("Отслеживаемые посылки не найдены.");
            return;
        }
        System.out.println("Новое местоположение посылки: ");
        String location = scanner.nextLine();
        trackables.forEach(parcel -> parcel.reportStatus(location));
    }

    private static void showBoxContents() {
        System.out.println("""
                Выберете коробку:
                1 — стандартные посылки
                2 — скоропортящиеся посылки
                3 — хрупкие посылки""");
        int box = Integer.parseInt(scanner.nextLine());
        processBox(box);
    }

    private static void processBox(int box) {
        switch (box) {
            case 1 -> {
                if (standardBox.getAllParcels().isEmpty()) {
                    System.out.println("Коробка стандартных посылок пуста.");
                    return;
                }
                System.out.println("Коробка со стандартными посылками:");
                System.out.println(standardBox.getAllParcels());
            }
            case 2 -> {
                if (perishableBox.getAllParcels().isEmpty()) {
                    System.out.println("Коробка скоропортящихся посылок пуста.");
                    return;
                }
                System.out.println("Коробка со скоропортящимися посылками:");
                System.out.println(perishableBox.getAllParcels());
            }
            case 3 -> {
                if (fragileBox.getAllParcels().isEmpty()) {
                    System.out.println("Коробка с хрупкими посылками пуста.");
                    return;
                }
                System.out.println("Коробка с хрупкими посылками:");
                System.out.println(fragileBox.getAllParcels());
            }
            default -> System.out.println("Неверный выбор.");
        }
    }

    private static void processUserInput(int parcelType) {
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        System.out.print("Вес: ");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.print("Адрес доставки: ");
        String deliveryAddress = scanner.nextLine();
        System.out.print("День отправки: ");
        int sendDay = Integer.parseInt(scanner.nextLine());
        switch (parcelType) {
            case 1 -> {
                StandardParcel standard = new StandardParcel(
                        description,
                        weight,
                        deliveryAddress,
                        LocalDate.of(2025, Month.NOVEMBER, sendDay)
                );
                allParcels.add(standard);
                standardBox.addParcel(standard);
            }
            case 2 -> {
                System.out.print("Срок годности дней: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishable = new PerishableParcel(
                        description,
                        weight,
                        deliveryAddress,
                        LocalDate.of(2025, Month.NOVEMBER, sendDay),
                        Duration.ofDays(timeToLive)
                );
                allParcels.add(perishable);
                perishableBox.addParcel(perishable);
            }
            case 3 -> {
                FragileParcel fragile = new FragileParcel(
                        description,
                        weight,
                        deliveryAddress,
                        LocalDate.of(2025, Month.NOVEMBER, sendDay)
                );
                allParcels.add(fragile);
                trackables.add(fragile);
                fragileBox.addParcel(fragile);
            }
            default -> System.out.println("Неверный выбор.");
        }
    }
}

