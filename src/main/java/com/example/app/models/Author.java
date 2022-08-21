package com.example.app.models;

import java.sql.ResultSet;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Author extends Model {

    @NotEmpty @Size(min = 5, max = 32)
    public String username;

    @NotEmpty
    public String name;

    @Email @NotEmpty
    public String email;

    @NotEmpty
    public String password;

    @NotNull
    public MultipartFile file;
    
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public Author(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Author(ResultSet rs){
        try {
            this.username = rs.getString("username");
            this.name = rs.getString("name");
            this.email = rs.getString("email");
            this.password = rs.getString("password");
        }
        catch(Exception e){}
    }
    
    public Author() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Author [email=" + email + ", name=" + name + ", password=" + password + ", username=" + username + "]";
    }
}
