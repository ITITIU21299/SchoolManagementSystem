package DAO;

import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Class.*;

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
    
    public List<Salary> getSalaryByStaffId(String id){
        List<Salary> salaries = new ArrayList<>();
        String query = "SELECT amount, salary_month, salary_year, payment_date, status FROM StaffSalary WHERE staff_id = ?";
        
        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            preparedStatement.setString(1, id);
            
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    Salary s = new Salary(rs.getString("amount"), rs.getString("salary_month"), rs.getString("salary_year"), rs.getString("payment_date"), rs.getString("status"));
                    salaries.add(s);
                }
                return salaries;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
