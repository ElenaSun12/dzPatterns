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
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты

        // Получаем текущую дату
        LocalDate date = LocalDate.now().plusDays(shift);

        // Форматируем дату в желаемый формат, например "dd.MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String generateCity(String locale) {
//     TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        String[] cities = new String[]{"Екатеринбург", "Казань", "Москва", "Воронеж", "Тула", "Великий Новгород", "Рязань"};
        return cities[new Random().nextInt(cities.length)];
    }
// Или:
//public static String generateCity(String locale) {
//    Faker faker = new Faker(new Locale(locale));
//    return faker.address().city();
//}

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker

        // Создаем экземпляр Faker с указанной локалью
        Faker faker = new Faker(new java.util.Locale(locale));

        // Получаем отдельно фамилию и имя
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();

        // Объединяем фамилию и имя
        String name = lastName + " " + firstName;
        return name;

    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker

        Faker faker = new Faker(new Locale(locale)); // Создаем экземпляр Faker с указанной локалью

        String phone = faker.phoneNumber().phoneNumber(); // Генерируем номер телефона

        return phone;   // Возвращаем сгенерированный номер телефона
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)

            // Генерируем данные для пользователя
            String city = generateCity(locale);
            String name = generateName(locale);
            String phone = generatePhone(locale);

            // Создаем и возвращаем объект UserInfo
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
