package com.springinaction.tacocloud.web.api;


import com.springinaction.tacocloud.model.Order;
import com.springinaction.tacocloud.model.Taco;
import com.springinaction.tacocloud.repository.OrderRepository;
import com.springinaction.tacocloud.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoAPIController {

    private TacoRepository tacoRepository;
    private OrderRepository orderRepository;


    EntityLinks entityLinks;

    @Autowired
    public DesignTacoAPIController(TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }





    @GetMapping("/recent")
    private Iterable<Taco> recentTacos()
    {

        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

        return (Iterable<Taco>) this.tacoRepository.findAll(page).iterator();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }


    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
