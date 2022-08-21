package com.example.app.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.app.models.Blog;

import jakarta.annotation.PostConstruct;

@Repository("blogs")
public class BlogManager implements Manager<Blog> {

    private final JdbcTemplate jdbc;

    @Autowired
    public BlogManager(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostConstruct
    public void init() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS Blogs (
                        id CHAR(36) PRIMARY KEY,
                        title TEXT,
                        content TEXT,
                        author VARCHAR(32),
                        FOREIGN KEY(author) REFERENCES Users(username)
                    )
                """.strip();
        jdbc.update(sql);
    }

    @Override
    public int create(Blog blog) {

        String sql = "INSERT INTO Blogs(id,title,content,author) VALUES (?,?,?,?) ON CONFLICT (id) DO UPDATE SET title = ?, content = ?";
        return jdbc.update(sql, blog.id, blog.title, blog.content, blog.author, blog.title, blog.content);
    }

    @Override
    public int update(Blog blog) {
        String sql = "UPDATE Blogs SET title = ?, content = ? WHERE id = ?";
        return jdbc.update(sql, blog.title, blog.content, blog.id);
    }

    @Override
    public int delete(String id) {
        String sql = "DELETE FROM Blogs WHERE id = ?";
        return jdbc.update(sql, id);
    }

    @Override
    public Blog read(String id) {
        try {
            Blog blog = jdbc.queryForObject(
                    "SELECT * FROM Blogs WHERE id = ?",
                    (rs, rowNum) -> new Blog(rs),
                    id);
            return blog;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Blog> list() {
        return jdbc.query("SELECT * FROM Blogs",
                (rs, rowNum) -> new Blog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
    }

    @Override
    public List<Blog> list(String author) {
        return jdbc.query("SELECT * FROM Blogs WHERE author = ?",
                (rs, rowNum) -> new Blog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)), author);
    }

}
