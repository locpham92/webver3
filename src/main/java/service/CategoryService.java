package service;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private List<Category> categoryList = new ArrayList<>();

    private Connection connection = ConnectToMySQL.getConnection();

    public List<Category> viewAll() {
        String sql = "SELECT * FROM category;";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                list.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add(Category category) {
        categoryList.add(category);
    }

    public void edit(int id, Category category) {
        int index = findIndexById(id);
        categoryList.set(index, category);
    }

    public void delete(int id) {
        int index = findIndexById(id);
        categoryList.remove(index);
    }

    int findIndexById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    int findIndexByName(String name) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Category findById(int id) {
        return categoryList.get(findIndexById(id));
    }

    public Category findByName(String name) {
        return categoryList.get(findIndexByName(name));
    }
}
