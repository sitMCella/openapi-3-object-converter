package de.mcella.openapi.v3.objectconverter.example;

public class Address {
    public String street;
    public String houseNumber;
    public String city;
    public String postCode;

    public Address(
        String street,
        String houseNumber,
        String city,
        String postCode
    ) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postCode = postCode;
    }
}
