package de.mcella.openapi.v3.objectconverter.example;

import java.util.List;

public class User {
    public final int age;
    public final String weigth;
    public final List<Address> addresses;

    public User(
        int age,
        String weigth,
        List<Address> addresses
    ) {
        this.age = age;
        this.weigth = weigth;
        this.addresses = addresses;
    }
}
