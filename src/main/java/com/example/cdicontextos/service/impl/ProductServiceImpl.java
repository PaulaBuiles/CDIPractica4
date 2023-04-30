package com.example.cdicontextos.service.impl;

import com.example.cdicontextos.annotations.MySqlConn;
import com.example.cdicontextos.model.Product;
import com.example.cdicontextos.repository.Repository;
import com.example.cdicontextos.repository.impl.ProductRepostoryImpl;
import com.example.cdicontextos.service.Service;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named("Service")
public class ProductServiceImpl implements Service<Product>, Serializable {

    @Inject
    @MySqlConn
    private ProductRepostoryImpl repository;
    public void main(String[] args) {
        /*list();
        byId(1L);
        save(repository);
        update(repository);
        delete(repository);*/

    }

    @Override
    public List<Product> list() throws SQLException {
        System.out.println("------------LIST PRODUCTS------------");

        return repository.list();
    }

    @Override
    public Product byId(Long id) throws SQLException {
        System.out.println("------------GET PRODUCT BY ID------------");
        return repository.byId(id);
    }

    @Override
    public void save(Product o) throws SQLException {
        System.out.println("------------SAVE PRODUCT------------");
        repository.save(o);
    }

    @Override
    public void delete(Long id) throws SQLException {
        System.out.println("------------DELETE PRODUCT------------");
        repository.delete(id);
    }

    @Override
    public void update(Long id,Product o) throws SQLException {
        System.out.println("------------UPDATE PRODUCT------------");
        repository.update(id,o);
    }
}
