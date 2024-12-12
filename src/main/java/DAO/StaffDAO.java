/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author nguye
 */
public class StaffDAO {
    public int getNumberOfStudentsByStaffId(String staffid){
        String query = "SELECT COUNT(DISTINCT ss.student_id) AS total_students "
                       + "FROM StaffSections sf "
                       + "JOIN StudentsSections ss ON sf.section_id = ss.section_id "
                       + "WHERE sf.staff_id = ?;";
        try (Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)){
            
            ps.setString(1, staffid);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()){
                System.out.println(resultSet.getInt("total_students"));
                return resultSet.getInt("total_students");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public String getStaffIdByUsername(String username){
        String query = "SELECT staff_id FROM Staff WHERE username = ?";
        try (Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)){
            
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()){
                return resultSet.getString("staff_id");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
