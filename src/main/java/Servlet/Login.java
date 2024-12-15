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

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            boolean isPasswordValid = PasswordUtil.verifyPassword(password, user.getPassword());
            if (isPasswordValid) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if (user.getRole().equals("staff")) {
                    StaffDAO staffDAO = new StaffDAO();
                    String staffId = staffDAO.getStaffIdByUsername(username);
                    session.setAttribute("staffId", staffId);

                    response.sendRedirect(request.getContextPath() + "/StaffPages");
                    return;
                }
                if (user.getRole().equals("student")) {
                    StudentDAO studentDAO = new StudentDAO();
                    Student student = studentDAO.getStudentByUsername(username);
                    session.setAttribute("username", username);

                    response.sendRedirect(request.getContextPath() + "/StudentPages");
                    return;
                }
            } else {
                request.setAttribute("errorMessage", "Invalid password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "User not found. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
