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
}
