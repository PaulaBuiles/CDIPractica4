package com.example.cdicontextos.service.impl;


import com.example.cdicontextos.model.Category;
import com.example.cdicontextos.model.Product;
import com.example.cdicontextos.repository.Repository;
import com.example.cdicontextos.service.Service;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
@SessionScoped
public class CatalogueService implements Service<Product>, Serializable {
    @Inject
    private Repository<Product> productRepository;
    @Inject
    private Repository<Category> categoryRepository;

    @Override
    public List<Product> list() throws SQLException {
        return productRepository.list();

    }
    @Override
    public Product byId(Long id) throws SQLException {
        return productRepository.byId(id);

    }

    @Override
    public void update(Long id, Product o) throws SQLException {
        productRepository.update(id, o);

    }

    @Override
    public void save(Product producto) throws SQLException {
        productRepository.save(producto);


    }
    @Override
    public void delete(Long id) throws SQLException {
        productRepository.delete(id);

    }


    public List<Category> listCategory() throws SQLException {
        return categoryRepository.list();
    }

    public Category byCategoryId(Long id) throws SQLException {
            return categoryRepository.byId(id);
    }

    public void guardarCategoria(Category categoria) throws SQLException    {
        categoryRepository.save(categoria);
    }

    public void eliminarCategoria(Long id) throws SQLException {
                categoryRepository.delete(id);
    }

    public void guardarProductoConCategoria(Product product, Category category) throws SQLException {
        categoryRepository.save(category);
        product.setCategory(category);
        productRepository.save(product);
    }

}