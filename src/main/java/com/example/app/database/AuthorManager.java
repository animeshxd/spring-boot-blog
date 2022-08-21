package com.example.app.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.app.models.Author;

import jakarta.annotation.PostConstruct;
// import static com.example.x.print;

@Repository("authors")
public class AuthorManager implements Manager<Author> {

    private final JdbcTemplate jdbc;

    @Autowired
    public AuthorManager(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostConstruct
    public void init() {
        // print("+++++++++++++++++++++","init called", "++++++++++++++++++++++++++++++++++++++++");
        String sql = """
                CREATE TABLE IF NOT EXISTS Authors(
                    username VARCHAR(32) PRIMARY KEY,
                    name TEXT,
                    email TEXT UNIQUE,
                    password TEXT
                )
                """;
        jdbc.update(sql);
    }


    @Override
    public int create(Author model) {
        String sql = """
            INSERT INTO Authors(username, name, email, password) VALUES (?,?,?,?)
                """;
        try{
            jdbc.update(sql, model.username, model.name, model.email, model.password);
        }catch(UncategorizedSQLException e){
            return -1;
        }
        return 1;
    }

    @Override
    public int update(Author model) {

        return 0;
    }

    @Override
    public int delete(String key) {

        return 0;
    }

    @Override
    public Author read(String username) {
        
        String sql = """
                SELECT * from Authors where username = ?
                """;
        try{
            return jdbc.queryForObject(sql, (rs, rowNum) -> new Author(rs), username);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Author> list() {

        return null;
    }

}
