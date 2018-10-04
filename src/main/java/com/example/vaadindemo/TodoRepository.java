package com.example.vaadindemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // derived delete query by Spring Data JPA
    @Transactional
    void deleteByDone(boolean done);
}