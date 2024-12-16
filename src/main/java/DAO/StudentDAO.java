package DAO;

import Class.*;
import Util.DBUtil;
import static java.lang.System.out;
import Class.Student;
import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class StudentDAO {

    public Student getStudentByUsername(String username) {
        String query = "SELECT * FROM Students WHERE username = ?";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(
                            resultSet.getString("student_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("date_of_birth"),
                            resultSet.getString("gender"),
                            resultSet.getString("class_id"),
                            resultSet.getString("academic_year")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Exam> getExamsByStudentId(String id) {
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT subject_name, schedule_date, start_time, end_time, room_number \n"
                + "FROM StudentsSections ss \n"
                + "JOIN RoomSchedule rs, Rooms r, ScheduleAssignment sa, Exams e, Sections se, Subjects su \n"
                + "WHERE sa.section_exam_id = e.exam_id \n"
                + "AND e.section_id = ss.section_id \n"
                + "AND sa.roomschedule_id = rs.roomschedule_id \n"
                + "AND rs.room_id = r.room_id \n"
                + "AND ss.section_id = se.section_id \n"
                + "AND se.subject_id = su.subject_id \n"
                + "AND student_id = ?;";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Exam e = new Exam(rs.getString("subject_name"), rs.getString("schedule_date"), rs.getString("start_time"), rs.getString("end_time"), rs.getString("room_number"));
                    exams.add(e);
                    System.out.println(e.getRoomNumber());
                }
                return exams;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Fee> getFeesByStudentId(String id) {
        List<Fee> fees = new ArrayList<>();
        String query = "SELECT * FROM StudentFees WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Fee f = new Fee(rs.getInt("semester"), rs.getInt("fee_year"), rs.getString("amount"), rs.getString("payment_date"), rs.getString("payment_status"));
                    fees.add(f);
                    out.println(f.getSemester());
                }
                return fees;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudentByUsernameProfile(String username) {
        Student student = null;
        String sql = "SELECT * FROM Students WHERE username = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setDateOfBirth("date_of_birth");
                student.setGender(rs.getString("gender"));
                student.setEmail(rs.getString("email"));
                student.setClassId(rs.getString("class_id"));
                student.setAcademicYear(rs.getString("academic_year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public boolean updateStudentProfile(Student student) {
        String sql = "UPDATE Students SET name = ?, date_of_birth = ?, gender = ?, email = ? WHERE student_id = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDateOfBirth());
            stmt.setString(3, student.getGender());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getStudentId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public float getMarksByStudentId(String id) {
        String query = "SELECT SUM(marks_obtained) as marks, COUNT(exam_id) as No FROM Grades WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return ((float) rs.getInt("marks") / rs.getInt("No"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Schedule> getRoomScheduleByStudentId(String id) {
        List<Schedule> schedules = new ArrayList<>();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start, end;
        String query = "SELECT subject_name, room_number, schedule_date, start_time, end_time \n"
                + "FROM StudentsSections ss \n"
                + "JOIN RoomSchedule rs, Rooms r, ScheduleAssignment sa, Sections se, Subjects su \n"
                + "WHERE ss.section_id = sa.section_exam_id \n"
                + "AND sa.roomschedule_id = rs.roomschedule_id \n"
                + "AND rs.room_id = r.room_id \n"
                + "AND ss.section_id = se.section_id \n"
                + "AND se.subject_id = su.subject_id \n"
                + "AND student_id = ?;";
        
        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(id);
            preparedStatement.setString(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    start = rs.getTime("start_time").toLocalTime();
                    end = rs.getTime("end_time").toLocalTime();

                    Schedule schedule = new Schedule(rs.getString("room_number"), rs.getString("subject_name"), rs.getString("schedule_date"), start.format(timeFormatter), end.format(timeFormatter));
                    schedules.add(schedule);
                }
                return schedules;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
