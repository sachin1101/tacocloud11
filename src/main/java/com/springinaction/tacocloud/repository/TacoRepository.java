package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.Resources;

public interface TacoRepository extends CrudRepository<Taco, Long> {

    Resources<Object> findAll(PageRequest page);
}
