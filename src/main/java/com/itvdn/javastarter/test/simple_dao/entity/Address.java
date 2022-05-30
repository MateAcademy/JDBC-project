package com.itvdn.javastarter.test.simple_dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private long addressID;
    private long userId;
    private String nameStreet;
    private int numberHome;

    public Address(String nameStreet, int numberHome) {
        this.nameStreet = nameStreet;
        this.numberHome = numberHome;
    }

    public Address(long clientId, String nameStreet, int numberHome) {
        this.clientId = clientId;
        this.nameStreet = nameStreet;
        this.numberHome = numberHome;
    }
}
