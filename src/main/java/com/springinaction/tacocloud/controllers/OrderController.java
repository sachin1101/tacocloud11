package com.springinaction.tacocloud.controllers;


import com.springinaction.tacocloud.model.Order;
import com.springinaction.tacocloud.model.Taco;
import com.springinaction.tacocloud.repository.TacoRepositoryDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("design")
public class OrderController {

    private TacoRepositoryDB tacoRepository;

    @Autowired
    public OrderController(TacoRepositoryDB tacoRepositoryDB) {
        this.tacoRepository = tacoRepositoryDB;
    }

    @GetMapping("/current")
    public String orderForm(Model model , RedirectAttributes redirectAttributes) {

        log.info(" 1:-is design Present::" + model.containsAttribute("design"));



        model.addAttribute("order", new Order());



        return "orderForm";
    }

    @PostMapping
    public String processOrder(Model model , @Valid Order order, Errors errors) {
        log.info("Error Count::" + errors.getErrorCount());
        if(errors.hasErrors())
        {
            return "orderForm";
        }
        log.info(" 1:-is design Present::" + model.containsAttribute("design"));

        Taco design = (Taco) model.asMap().get("design");

        order.addToTacoList(design);

        log.info("Taco List ::" + order.getTacoList());
        //tacoRepository.save()

     //   List<Taco> updatedTacoList = new ArrayList<>()


        order.getTacoList().forEach((taco)->{tacoRepository.save(taco);
        });

        System.out.println(order);

        log.info("Order submitted: " + order);
        return "redirect:/";
    }

}
