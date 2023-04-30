package com.example.cdicontextos.repository.impl;

import com.example.cdicontextos.annotations.MySqlConn;
import com.example.cdicontextos.model.Category;
import com.example.cdicontextos.repository.Repository;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryRepositoryImpl implements Repository<Category> {


    @Inject
    @MySqlConn
    private Connection coon;

    public static final Logger log = Logger.getLogger(CategoryRepositoryImpl.class.getName());
    private Category createCategory(ResultSet resultSet) throws SQLException {
        Category category=new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("category_name"));
        return category;
    }
    @Override
    public List<Category> list() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement=coon.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM categories")
        ){
            while (resultSet.next()) {
                Category category = createCategory(resultSet);
                categories.add(category);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category byId(Long id) {
        Category category = null;
        try (PreparedStatement preparedStatement=coon.prepareStatement("SELECT * FROM categories where id=?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                category = createCategory(resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category save(Category o) {
        String sql = null;
        if (o.getId() != null && o.getId() > 0) {
            sql = "UPDATE categories SET nombre_categorie=? WHERE id=?";
        } else {
            sql = "INSERT INTO categories(nombre_categorie) VALUES(?)";
        }
        try (PreparedStatement stmt = coon.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, o.getName());
            if (o.getId() != null && o.getId() > 0) {
                stmt.setInt(2,o.getId());
            }
            stmt.executeUpdate();
            if (o.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        o.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return o;

    }

    @Override
    public void delete(Long id) {
        Category category = null;
        try (PreparedStatement preparedStatement=coon.prepareStatement("DELETE FROM categories WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Long id,Category o) {
        Category category = (Category) o;
        try (PreparedStatement preparedStatement=coon.prepareStatement("UPDATE categories SET nombre_categorie=? where id=?")){
            preparedStatement.setString(1,category.getName());
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
