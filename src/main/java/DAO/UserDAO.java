/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nguye
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Class.User;
import Util.DBUtil;

public class UserDAO {
    public User getUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
}
