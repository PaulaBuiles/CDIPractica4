package com.example.cdicontextos.repository.impl;


import com.example.cdicontextos.annotations.MySqlConn;
import com.example.cdicontextos.model.Category;
import com.example.cdicontextos.model.Product;
import com.example.cdicontextos.repository.Repository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Named("ProductRepositoryImpl")
public class ProductRepostoryImpl implements Repository<Product> {
    @Inject
    @MySqlConn
    private Connection conn;
    /*private Connection getConnection() throws SQLException {
        return ConnectionBD.getInstance();
    }*/

    private Product createProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setProductName(resultSet.getString("nombre"));
        product.setPrice(resultSet.getDouble("precio"));
        product.setDateRegister(resultSet.getDate("fecha_registro").toLocalDate());
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("nombre_categorie"));
        product.setCategory(category);
        return product;
    }
    @Override
    public List<Product> list() {
        List<Product> products = new ArrayList<>();
        try (Statement statement=conn.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT p.*,c.nombre_categorie as name from products as p inner join categories as c ON (p.categorie_id=c.id)")
        ){
            while (resultSet.next()) {
                Product product = createProduct(resultSet);
                products.add(product);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    @Override
    public Product byId(Long id) {
        Product producto = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT p.*,c.categorie_id as id from products as p inner join categories as c ON (p.categorie_id=c.id)")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                producto = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public Product save(Product product) throws SQLException {
        String sql;
        if(product.getId() != null && product.getId()>0){
            sql = "UPDATE products SET name=?, price=?, categorie_id=? WHERE id=?";
        }else{
            sql ="INSERT INTO products(name, price, categorie_id, date_register) VALUES(?,?,?,?)";
        }
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setLong(3, product.getCategory().getId());

            if(product.getId() != null && product.getId()>0){
                preparedStatement.setLong(4,product.getId());
            }else{
                preparedStatement.setDate(4,Date.valueOf(product.getDateRegister()));
            }
            preparedStatement.executeUpdate();
        }
        return product;
    }


    @Override
    public void delete(Long id) {
        Product producto = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE from products WHERE id=?")) {
            preparedStatement.setLong(1, id);
            int resultSet = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Product product) throws SQLException {
        Product producto = (Product) product;
        try (PreparedStatement preparedStatement=conn.prepareStatement("UPDATE products SET id=? ,nombre=?,precio=?,fecha_registro=? where id=?")){
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setLong(2,product.getPrice().longValue());
            preparedStatement.setDate(3,Date.valueOf(product.getDateRegister()));
            preparedStatement.setLong(4,product.getCategory().getId());
            preparedStatement.setLong(5,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
