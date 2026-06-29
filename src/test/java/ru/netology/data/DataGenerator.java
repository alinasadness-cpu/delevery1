package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final Random random = new Random();

    private DataGenerator() {
    }

    public static String generateDate(int plusDays) {
        return LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Новосибирск", "Екатеринбург"};
        return cities[random.nextInt(cities.length)];
    }

    public static String generateName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone() {
        return "+7" + faker.number().numberBetween(900, 999) + faker.number().numberBetween(1000000, 9999999);
    }

    // ДОБАВЬТЕ ЭТОТ МЕТОД:
    public static UserInfo generateUser() {
        return new UserInfo(
                generateCity(),
                generateDate(3),
                generateName(),
                generatePhone()
        );
    }
}