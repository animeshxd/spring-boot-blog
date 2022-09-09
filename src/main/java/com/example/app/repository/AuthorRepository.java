package com.example.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.app.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
    
}
