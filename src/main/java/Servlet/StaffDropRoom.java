package Servlet;

import Class.*;
import DAO.*;
import Util.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.conf.PropertyKey;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "StaffDropRoom", urlPatterns = {"/StaffDropRoom"})
public class StaffDropRoom extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomScheduleDAO roomScheduleDAO = new RoomScheduleDAO();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        StaffDAO staffDAO = new StaffDAO();
        Staff staff = staffDAO.getStaffByUsername(user.getUsername());

        List<AssignedRoom> assignedRooms = roomScheduleDAO.getRoomByStaffId(staff.getStaffId());

        request.setAttribute("assignedRooms", assignedRooms);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/staffsectionmanagement.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomscheduleId = request.getParameter("scheduleId");

        if (roomscheduleId == null || roomscheduleId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Room Schedule ID is required");
            return;
        }

        boolean isDropped = dropRoomSchedule(roomscheduleId);

        if (!isDropped) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to drop the room schedule");
        } else {
            processRequest(request, response);
        }
    }

    private boolean dropRoomSchedule(String roomscheduleId) {
        String query = "UPDATE RoomSchedule SET section_id = NULL WHERE roomschedule_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, roomscheduleId);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
