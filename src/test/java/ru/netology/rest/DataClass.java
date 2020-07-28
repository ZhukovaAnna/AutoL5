package ru.netology.rest;

import com.github.javafaker.Faker;

import java.util.*;

public class DataClass {
    DataClass() {}

    public static String getCity() {
        Random rand = new Random();
        List<String> list = Arrays.asList("Москва", "Санкт-Петербург", "Казань", "Воронеж", "Волгоград", "Екатеринбург", "Саратов", "Новосибирск", "Владикавказ", "Владивосток", "Ярославль", "Краснодар");
        int randomIndex = rand.nextInt(list.size());
        String randomElement = list.get(randomIndex);
        return randomElement;
    }

    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getPhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }
}



