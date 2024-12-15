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
import java.util.List;

@WebServlet(name = "StaffPages", urlPatterns = {"/StaffPages"})
public class StaffPages extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        String staffId = (String) session.getAttribute("staffId");
        if (staffId == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        StaffDAO staffDAO = new StaffDAO();
        RoomScheduleDAO roomScheduleDAO = new RoomScheduleDAO();

        int totalStudents = staffDAO.getNumberOfStudentsByStaffId(staffId);
        session.setAttribute("totalStudents", totalStudents);

        List<Salary> salaries = staffDAO.getSalaryByStaffId(staffId);
        session.setAttribute("salaries", salaries);

        Staff staff = staffDAO.getStaffByStaffId(staffId);
        session.setAttribute("staff", staff);

        response.sendRedirect(request.getContextPath() + "/staffpages.jsp");
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
