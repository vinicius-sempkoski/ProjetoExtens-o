package com.example.trabalhopontodevenda.dao;

import java.util.ArrayList;

public interface IGenericDao<Object> {

    long insert(Object obj);
    long update(Object obj);
    long delete(Object obj);
    ArrayList<Object> getAll();
    Object getById(int id);
    Object getById(String user);

}
