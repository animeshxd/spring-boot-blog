package com.example.app.models;

import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Author {
    
    @Id
    @NotEmpty @Size(min = 5, max = 32)
    private String username;

    @NotEmpty
    private String name;

    @Email @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @Lob
    private Blob file;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Blog> blogs = new LinkedList<>();

    @Transient
    private MultipartFile xfile;

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

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public MultipartFile getXfile() {
        return xfile;
    }

    public void setXfile(@NotNull MultipartFile xfile) {
        this.xfile = xfile;
    }

    public Author(String username) {
        this.username = username;
    }

    public Author() {
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Author [email=" + email + ", name=" + name + ", password=" + password + ", username=" + username + "]";
    }

    
    
    
    
}
