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

@WebServlet(name = "StaffAssignRoom", urlPatterns = {"/StaffAssignRoom"})
public class StaffAssignRoom extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RoomScheduleDAO roomScheduleDAO = new RoomScheduleDAO();
        StaffDAO staffDAO = new StaffDAO();
        Staff staff = (Staff) session.getAttribute("staff");
        
        List<Room> rooms = roomScheduleDAO.getAvailableRooms();
        request.setAttribute("rooms", rooms);

        List<Section> sections = staffDAO.getSectionByStaffId(staff.getStaffId());
        request.setAttribute("sections", sections);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/staffsectionassign.jsp");
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
        String roomScheduleId = request.getParameter("roomScheduleId");
        String sectionId = request.getParameter("sectionId");

        if (roomScheduleId != null && !roomScheduleId.isEmpty()
                && sectionId != null && !sectionId.isEmpty()) {

            RoomScheduleDAO roomScheduleDAO = new RoomScheduleDAO();
            boolean isAssigned = roomScheduleDAO.assignSectionToRoom(roomScheduleId, sectionId);

            if (isAssigned) {
                processRequest(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to assign section");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }
}
