package com.springinaction.tacocloud.model;

import lombok.Data;

import java.util.List;

@Data
public class Taco {

     List<String> ingredientList;
    String name;

}
