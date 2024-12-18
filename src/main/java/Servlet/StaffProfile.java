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

@WebServlet(name = "StaffProfile", urlPatterns = {"/StaffProfile"})
public class StaffProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        StaffDAO staffDAO = new StaffDAO();
        Staff staff = staffDAO.getStaffByUsername(user.getUsername());
        request.setAttribute("staff", staff);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/staffprofile.jsp");
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
        String username = request.getParameter("username");
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        if (username != null) {
            Staff staff = new Staff();
            staff.setName(name);
            staff.setEmail(email);
            staff.setPhone(phone);
            staff.setAddress(address);
            
            StaffDAO staffDAO = new StaffDAO();
            boolean updated = staffDAO.updateStaffProfile(staff);
            request.setAttribute("staff", staff);
            response.sendRedirect(request.getContextPath() + "/StaffProfile");
        }

    }

}
