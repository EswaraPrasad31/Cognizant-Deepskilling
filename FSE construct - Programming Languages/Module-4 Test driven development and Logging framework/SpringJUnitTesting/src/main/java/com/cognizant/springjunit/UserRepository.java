package com.cognizant.springjunit;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    User findByName(String name);

}