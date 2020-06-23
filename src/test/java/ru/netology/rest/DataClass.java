package ru.netology.rest;

import com.github.javafaker.Faker;

import java.util.*;

public class DataClass {
    DataClass() {}

    private enum Cities {
            Москва, Санкт_Петербург, Казань, Воронеж, Волгоград, Екатеринбург, Саратов, Новосибирск, Владикавказ, Владивосток, Ярославль, Краснодар;
    }
            private static final List<Cities> values = Collections.unmodifiableList(Arrays.asList(Cities.values()));
            private static final int size = values.size();
            private static final Random random = new Random(11);
            public static String randomCity() {
            return values.get(random.nextInt(size)).toString();
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



