package Servlet;

import Class.*;
import DAO.*;
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
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.util.*;

public class StaffSchedule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

        StaffDAO staffDAO = new StaffDAO();
        Staff staff = staffDAO.getStaffByUsername(user.getUsername());

        List<Schedule> schedules = staffDAO.getRoomScheduleByStaffId(staff.getStaffId());
        request.setAttribute("schedules", schedules);

        String week = "";
        String current_week = request.getParameter("current week");        
        String action = "";
        if (request.getParameter("action")!=null) 
            action = request.getParameter("action");
        if (action.equals("Get week")) {
            if (request.getParameter("week") != null) {
                week = request.getParameter("week");
                request.setAttribute("week", week);
            } else {
                request.setAttribute("week", current_week);
            }
        } else {
            if (action.equals("Previous week")) {
                int tmp = Integer.parseInt(current_week);
                week = String.valueOf(tmp - 1);
                request.setAttribute("week", week);                
                out.println("PREV WEEK: " + week);                
            } else if (action.equals("Next week")) {
                int tmp = Integer.parseInt(current_week);
                week = String.valueOf(tmp + 1);
                request.setAttribute("week", week);
                out.println("NEXT WEEK: " + week);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/staffschedule.jsp");
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
        processRequest(request, response);
    }

}
