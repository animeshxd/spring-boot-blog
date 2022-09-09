package com.example.app.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.app.models.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog, UUID> {


}
