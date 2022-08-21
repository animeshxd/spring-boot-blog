package com.example.app.database;

import java.util.Collections;
import java.util.List;

import com.example.app.models.Model;

public interface Manager<T extends Model> {
    public void init();
    public int  create(T model);
    public int  update(T model);
    public int  delete(String key);
    public T read(String key);
    public List<T> list();
    default public List<T> list(String id) { return Collections.<T>emptyList(); };

}
