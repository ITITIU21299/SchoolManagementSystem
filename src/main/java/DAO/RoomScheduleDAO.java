package DAO;

import Class.*;
import Util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomScheduleDAO {

    private Connection connection;

    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT rs.roomschedule_id, r.room_id, r.room_number, r.room_type, r.capacity, "
                + "rs.schedule_date, rs.start_time, rs.end_time FROM Rooms r "
                + "LEFT JOIN RoomSchedule rs ON r.room_id = rs.room_id "
                + "WHERE rs.section_id IS NULL";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Room r = new Room(
                            rs.getString("roomschedule_id"),
                            rs.getString("room_id"),
                            rs.getString("room_number"),
                            rs.getString("room_type"),
                            rs.getString("schedule_date"),
                            rs.getString("capacity"),
                            rs.getString("start_time"),
                            rs.getString("end_time"));
                    rooms.add(r);
                }
                return rooms;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AssignedRoom> getRoomByStaffId(String staffId) {
        List<AssignedRoom> rooms = new ArrayList<>();
        String query = "SELECT rs.roomschedule_id, r.room_id, r.room_number, r.room_type, r.capacity, "
                + "rs.schedule_date, rs.start_time, rs.end_time, s.section_group, sub.subject_name FROM Rooms r "
                + "LEFT JOIN RoomSchedule rs ON r.room_id = rs.room_id "
                + "LEFT JOIN Sections s ON s.section_id = rs.section_id "
                + "LEFT JOIN Subjects sub ON sub.subject_id = s.subject_id "
                + "LEFT JOIN StaffSections ss ON rs.section_id = ss.section_id "
                + "WHERE ss.staff_id = ?";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, staffId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    AssignedRoom r = new AssignedRoom(
                            rs.getString("roomschedule_id"),
                            rs.getString("room_id"),
                            rs.getString("room_number"),
                            rs.getString("room_type"),
                            rs.getString("schedule_date"),
                            rs.getString("capacity"),
                            rs.getString("start_time"),
                            rs.getString("end_time"),
                            rs.getString("section_group"),
                            rs.getString("subject_name"));
                    rooms.add(r);
                }
                return rooms;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean assignSectionToRoom(String roomScheduleId, String sectionId) {
        String query = "UPDATE RoomSchedule SET section_id = ? WHERE roomschedule_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, sectionId);
            preparedStatement.setString(2, roomScheduleId);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
