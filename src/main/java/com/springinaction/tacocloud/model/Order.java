package com.springinaction.tacocloud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
public class Order {

    String name;
    String street;
    String city;
    String state;
    String zip;
    String ccNumber;
    String ccExpiration;
    String ccCVV;

}
