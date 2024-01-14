package com.slamy.app.repositories;

import com.slamy.app.models.Email;
import com.slamy.app.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstNameAndLastName(String firstName, String lastName);
    User findByEmail(Email email);
    List<User> findAllBy();
}

