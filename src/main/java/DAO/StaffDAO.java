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

    public int getNumberOfStudentsByStaffId(String staffid) {
        String query = "SELECT COUNT(DISTINCT ss.student_id) AS total_students "
                + "FROM StaffSections sf "
                + "JOIN StudentsSections ss ON sf.section_id = ss.section_id "
                + "WHERE sf.staff_id = ?;";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, staffid);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("total_students");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getStaffIdByUsername(String username) {
        String query = "SELECT staff_id FROM Staff WHERE username = ?";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("staff_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Salary> getSalaryByStaffId(String id) {
        List<Salary> salaries = new ArrayList<>();
        String query = "SELECT amount, salary_month, salary_year, payment_date, status FROM StaffSalary WHERE staff_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Salary s = new Salary(
                            rs.getString("amount"),
                            rs.getString("salary_month"),
                            rs.getString("salary_year"),
                            rs.getString("payment_date"),
                            rs.getString("status"));
                    salaries.add(s);
                }
                return salaries;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStaffProfile(Staff staff) {
        String sql = "UPDATE Staff SET name = ?, email = ?, phone = ?, address = ? WHERE staff_id = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPhone());
            stmt.setString(4, staff.getAddress());
            stmt.setString(5, staff.getStaffId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Staff getStaffByStaffId(String staffId) {
        String query = "SELECT * FROM Staff WHERE staff_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, staffId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Staff(
                            resultSet.getString("staff_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("address"),
                            resultSet.getString("joining_date"),
                            resultSet.getString("qualification")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Section> getSectionByStaffId(String staffId) {
        List<Section> sections = new ArrayList<>();
        String query = "SELECT s.section_id, s.section_group, sub.subject_name, sub.subject_year, sub.semester "
                + "FROM Sections s "
                + "JOIN StaffSections ss ON s.section_id = ss.section_id "
                + "JOIN Subjects sub ON s.subject_id = sub.subject_id "
                + "WHERE ss.staff_id = ?;";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, staffId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Section s = new Section(
                            rs.getString("section_id"),
                            rs.getString("subject_name"),
                            rs.getString("section_group"),
                            rs.getString("subject_year"),
                            rs.getString("semester"));
                    sections.add(s);
                }
                return sections;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
