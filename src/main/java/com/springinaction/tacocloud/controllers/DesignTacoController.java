package com.springinaction.tacocloud.controllers;


import com.springinaction.tacocloud.model.Ingredient;
import com.springinaction.tacocloud.model.Ingredient.Type;
import com.springinaction.tacocloud.model.Taco;
import com.springinaction.tacocloud.repository.IngredientRepositoryDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepositoryDB ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepositoryDB ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }


    @GetMapping
    public String showTacoDesignForm(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Grounded peanuts Paste", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
//        Type[] types = Ingredient.Type.values();
//        for (Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(),
//                    filterByType(ingredients, type));
//        }



        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> {ingredients.add(i);
            System.out.println(i);});

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }


        model.addAttribute("design", new Taco());

        return "tacoDesignForm";
    }



    @PostMapping
    public String processDesign(@Valid Taco design , Errors errors) {



        log.info("Processing design: " + design);
        log.info("Error Count::" + errors.getErrorCount());

        if(errors.hasErrors())
        {
           errors.getAllErrors().forEach((k)->{log.error(k.toString());});
           return "tacoDesignForm";
        }


        return "redirect:/orders/current";
    }

    private Object filterByType(List<Ingredient> ingredients, Type type) {

        List<Ingredient> returnList = new ArrayList<>();

        ingredients.forEach((k)->{
                if(k.getType().equals(type)) { returnList.add(k);}        });

        return  returnList;

    }
}
