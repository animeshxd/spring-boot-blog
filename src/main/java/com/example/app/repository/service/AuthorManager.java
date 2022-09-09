package com.example.app.repository.service;

import java.io.IOException;
import java.sql.Blob;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.models.Author;
import com.example.app.repository.AuthorRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class AuthorManager {
    
    @Autowired
    private AuthorRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    
    
    public AuthorManager(AuthorRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    Session session(){
        return entityManager.unwrap(Session.class);
    }

    @Transactional
    public Author save(Author author) throws HibernateException, IOException{
        MultipartFile xfile = author.getXfile();
        Blob file = session().getLobHelper().createBlob(xfile.getInputStream(), xfile.getSize());
        author.setFile(file);
        repository.save(author);
        return author;
    }

    
}
