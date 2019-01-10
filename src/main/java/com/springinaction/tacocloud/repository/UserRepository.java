package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findUserByUsername(String username);
}
