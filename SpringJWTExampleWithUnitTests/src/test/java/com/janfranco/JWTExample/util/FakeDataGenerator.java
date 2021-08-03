package com.janfranco.JWTExample.util;

import com.github.javafaker.Faker;
import com.janfranco.JWTExample.entity.User;

public class FakeDataGenerator {

    public static User generateFakeUser() {
        Faker faker = new Faker();
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .name(faker.name().firstName())
                .build();
    }
}
