package Servlet;

import Class.*;
import DAO.*;
import Util.*;
import java.util.*;
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
        String[] weekdays = request.getParameterValues("weekdays");
        String[] weeks = request.getParameterValues("weeks");

        if (roomScheduleId != null && !roomScheduleId.isEmpty()
                && sectionId != null && !sectionId.isEmpty()) {

            RoomScheduleDAO roomScheduleDAO = new RoomScheduleDAO();
            Map<String, String> assignmentResults = new HashMap<>();

            for (String week : weeks) {
                for (String weekday : weekdays) {
                    boolean isAssigned = roomScheduleDAO.assignSectionToRoomOrCreate(roomScheduleId, sectionId, Integer.parseInt(weekday), Integer.parseInt(week));
                    if (isAssigned) {
                        assignmentResults.put("Week " + week + ", Day " + weekday, "Success");
                    } else {
                        assignmentResults.put("Week " + week + ", Day " + weekday, "Failed (Conflicting schedule)");
                    }
                }
            }
            request.setAttribute("assignmentResults", assignmentResults);

            HttpSession session = request.getSession(false);
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = (Staff) session.getAttribute("staff");

            List<Room> rooms = roomScheduleDAO.getAvailableRooms();
            request.setAttribute("rooms", rooms);

            List<Section> sections = staffDAO.getSectionByStaffId(staff.getStaffId());
            request.setAttribute("sections", sections);

            request.getRequestDispatcher("staffsectionassign.jsp").forward(request, response);

            //String message = roomScheduleDAO.assignSectionToRoom(roomScheduleId, sectionId, weekdays, weeks);
//            if (message != null) {
//                request.setAttribute("message", message);
//                processRequest(request, response);
//            } else {
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to assign section");
//            }
//        } else {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
//        }
//            if (isAssigned) {
//                processRequest(request, response);
//            } else {
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to assign section");
//            }
//        } else {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
//        }
        }
    }
}
