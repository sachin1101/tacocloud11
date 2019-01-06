package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.Taco;

public interface TacoRepositoryDB  {

    Taco save(Taco design);
}
