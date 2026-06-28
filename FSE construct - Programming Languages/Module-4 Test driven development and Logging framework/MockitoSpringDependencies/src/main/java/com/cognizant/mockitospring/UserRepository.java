package com.cognizant.mockitospring;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

}