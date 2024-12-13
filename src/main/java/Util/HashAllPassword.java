package Util;

import java.sql.*;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashAllPassword {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement selectStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection
            conn = DBUtil.getConnection();

            // Query to select users with plain-text passwords
            String selectQuery = "SELECT username, password FROM Users WHERE password IS NOT NULL";
            selectStmt = conn.prepareStatement(selectQuery);
            rs = selectStmt.executeQuery();

            // Query to update hashed passwords
            String updateQuery = "UPDATE Users SET password = ? WHERE username = ?";
            updateStmt = conn.prepareStatement(updateQuery);

            // Process each user
            while (rs.next()) {
                String username = rs.getString("username");
                String plainPassword = rs.getString("password");

                // Hash the password using BCrypt
                String hashedPassword = BCrypt.withDefaults().hashToString(12, plainPassword.toCharArray());

                // Update the hashed password back to the database
                updateStmt.setString(1, hashedPassword);
                updateStmt.setString(2, username);
                updateStmt.executeUpdate();

                System.out.println("Password hashed and updated for user: " + username);
            }

            System.out.println("All passwords hashed successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (selectStmt != null) selectStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
