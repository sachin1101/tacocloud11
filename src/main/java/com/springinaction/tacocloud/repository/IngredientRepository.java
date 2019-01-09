package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface  IngredientRepository extends CrudRepository<Ingredient, String> {
}
