package service;

import model.User;
import model.submodel.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Connection connection = ConnectToMySQL.getConnection();

    private List<User> userList = new ArrayList<>();
    private RoleService roleService = new RoleService();

    public UserService() {
    }

    public void add(User user) {
        String sql = "insert into user(username, password, idrole) values (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            Role role = user.getRole();
            preparedStatement.setInt(3,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> viewAll() {
        String sql = "select user.*, r.name as nameRole from user join role r on r.id = user.idRole;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int idRole = rs.getInt("idRole");
                String nameRole = rs.getString("nameRole");
                Role role = new Role(idRole, nameRole);
                User user = new User(id, username, password, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public boolean checkUser(String username, String password) {
        String sql = "select * from user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String usernameRS = rs.getString("username");
                String passwordRS = rs.getString("password");
                if (usernameRS.equals(username) && passwordRS.equals(password)) {
                return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int checkRole(String username, String password) {
        String sql = "select user.*, r.name as nameRole from user join role r on r.id = user.idRole;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String usernameRS = rs.getString("username");
                String passwordRS = rs.getString("password");
                String nameRole = rs.getString("nameRole");
                if (nameRole == "admin") {
                    return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public void edit(int id, User user) {
        String sql = "UPDATE user SET username = ?, password = ?, idrole = ? WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            Role role = user.getRole();
            preparedStatement.setInt(3, role.getId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findById(int id) {
        String sql = "select user.*, r.name as nameRole from user join role r on r.id = user.idRole where user.id = ?;";
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nameCategory = rs.getString("nameCategory");
                int idRole = rs.getInt("idRole");
                String nameRole = rs.getString("nameRole");
                Role role = new Role(idRole, nameRole);
                user = new User(id, username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public int getIdUser(String username, String password) {
        String sql = "select * from user where username = ? and password = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
