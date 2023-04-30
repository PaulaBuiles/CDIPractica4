package com.example.cdicontextos.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> list() throws SQLException;
    T byId(Long id) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
    void update(Long id,T o) throws SQLException;
}
