package com.springinaction.tacocloud.service;

import com.springinaction.tacocloud.model.User;
import com.springinaction.tacocloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        log.info("Input User::" + username );
        User user = userRepository.findUserByUsername(username);

        if(user==null)
        {
            throw new UsernameNotFoundException("No User Details found for " + username);
        }
        log.info("Ret User::" + user );
        return user;
    }
}
