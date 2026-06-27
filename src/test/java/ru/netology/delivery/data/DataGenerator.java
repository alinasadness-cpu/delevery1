package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        LocalDate date = LocalDate.now().plusDays(shift);
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Новосибирск",
                "Екатеринбург", "Нижний Новгород", "Челябинск", "Самара"};
        Random random = new Random();
        return cities[random.nextInt(cities.length)];
    }

    public static String generateName(Faker faker) {
        return faker.name().fullName();
    }

    public static String generatePhone(Faker faker) {
        return "+7" + faker.phoneNumber().subscriberNumber(10);
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            return new UserInfo(
                    generateCity(),
                    generateName(faker),
                    generatePhone(faker)
            );
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}