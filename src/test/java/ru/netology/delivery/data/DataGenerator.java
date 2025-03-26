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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String generateCity(String locale) {

        String[] cities = new String[]{"Екатеринбург", "Казань", "Москва", "Воронеж", "Тула", "Великий Новгород", "Рязань"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {

        Faker faker = new Faker(new java.util.Locale(locale));
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();

        String name = lastName + " " + firstName;
        return name;

    }

    public static String generatePhone(String locale) {

        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {

            String city = generateCity(locale);
            String name = generateName(locale);
            String phone = generatePhone(locale);
            return new UserInfo(city, name, phone);
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
