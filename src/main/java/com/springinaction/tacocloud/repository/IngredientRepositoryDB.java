package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.Ingredient;

import java.util.List;

public interface IngredientRepositoryDB {

    List<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);

}

