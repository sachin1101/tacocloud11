package com.springinaction.tacocloud.controllers;


import com.springinaction.tacocloud.model.Order;
import com.springinaction.tacocloud.model.Taco;
import com.springinaction.tacocloud.model.User;
import com.springinaction.tacocloud.property.OrderProperties;
import com.springinaction.tacocloud.repository.OrderListRepositoryDB;
import com.springinaction.tacocloud.repository.TacoRepositoryDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@ConfigurationProperties(prefix="taco.orders")
public class OrderController {



    private TacoRepositoryDB tacoRepository;
    private OrderListRepositoryDB orderListRepositoryDB;

    private OrderProperties orderProperties;


    @Autowired
    public OrderController(TacoRepositoryDB tacoRepository, OrderListRepositoryDB orderListRepositoryDB, OrderProperties orderProperties) {
        this.tacoRepository = tacoRepository;
        this.orderListRepositoryDB = orderListRepositoryDB;
        this.orderProperties = orderProperties;
    }



    @GetMapping("/current")
    public String orderForm(Model model , RedirectAttributes redirectAttributes) {

        log.info(" 1:-is design Present::" + model.containsAttribute("design"));



        model.addAttribute("order", new Order());



        return "orderForm";
    }

    @PostMapping
    public String processOrder(Model model , @Valid Order order, Errors errors, @AuthenticationPrincipal User user) {
        /*

        Authentication authentication =
SecurityContextHolder.getContext().getAuthentication();
User user = (User) authentication.getPrincipal();

         */


        log.info("Error Count::" + errors.getErrorCount());
        if(errors.hasErrors())
        {
            return "orderForm";
        }
        log.info(" 1:-is design Present::" + model.containsAttribute("design"));

        Taco design = (Taco) model.asMap().get("design");

        order.addToTacoList(design);

        order.setUser(user);
        log.info("Taco List ::" + order.getTacoList());
        //tacoRepository.save()

     //   List<Taco> updatedTacoList = new ArrayList<>()


        order.getTacoList().forEach((taco)->{tacoRepository.save(taco);
        });

        orderListRepositoryDB.save(order);

        System.out.println(order);

        log.info("Order submitted: " + order);
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize()).next();
        model.addAttribute("orders",
                orderListRepositoryDB.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

}
