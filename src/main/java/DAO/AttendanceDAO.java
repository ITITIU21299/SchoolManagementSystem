/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Class.Attendance;
import Class.Room;
import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author huynh
 */
public class AttendanceDAO {

    public Map<String, List<Attendance>> getAttendanceByStudentId(String studentId) {
        Map<String, List<Attendance>> attendanceMap = new HashMap<>();
        String sql = "SELECT section_id, session_number, status FROM Attendance WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String sectionId = rs.getString("section_id");
                int sessionNumber = rs.getInt("session_number");
                String status = rs.getString("status");

                Attendance attendance = new Attendance(sessionNumber, sectionId, status);

                attendanceMap.computeIfAbsent(sectionId, k -> new ArrayList<>()).add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceMap;
    }

    public Map<String, String> getAttendance(String sectionId, int weekNumber) {
        Map<String, String> attendance = new HashMap<>();
        String query = "SELECT student_id, status FROM Attendance "
                + "WHERE section_id = ? AND session_number = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, sectionId);
            stmt.setInt(2, weekNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                attendance.put(rs.getString("student_id"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    public boolean saveAttendance(String sectionId, int weekNumber, Map<String, String> studentAttendance) {
        String insertQuery = "INSERT INTO Attendance (student_id, section_id, session_number, status) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE status = ?";
        Connection connection = null;
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
                for (Map.Entry<String, String> entry : studentAttendance.entrySet()) {
                    stmt.setString(1, entry.getKey());
                    stmt.setString(2, sectionId);
                    stmt.setInt(3, weekNumber);
                    stmt.setString(4, entry.getValue());
                    stmt.setString(5, entry.getValue());
                    stmt.addBatch();
                }
                stmt.executeBatch();
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
