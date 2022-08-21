package com.example.app.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.validation.constraints.NotEmpty;

public class Blog extends Model{
	
	public Blog(String id, String title, String content, String author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}
	@NotEmpty
	public String id;
	@NotEmpty
	public String title;
	@NotEmpty
	public String content;
	public String author;
	
	public Blog(ResultSet rs) {
		try {
			this.id = rs.getString("id");
			this.title = rs.getString("title");
			this.content = rs.getString("content");
			this.author = rs.getString("author");
		} catch (SQLException e) {}

	}

	public Blog(){}

	public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
