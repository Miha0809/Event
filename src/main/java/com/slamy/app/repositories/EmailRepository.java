package com.slamy.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.slamy.app.models.Email;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {
    boolean existsByName(String name);
    Email findByName(String name);
    List<Email> findAllBy();
}
