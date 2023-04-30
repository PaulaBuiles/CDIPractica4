package com.example.cdicontextos.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T>{
    List<T> list() throws SQLException;
    T byId(Long id) throws SQLException;
    T save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
    void update(Long id,T t) throws SQLException;
}
