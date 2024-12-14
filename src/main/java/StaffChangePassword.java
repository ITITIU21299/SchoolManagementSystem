/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author huynh
 */
@WebServlet(urlPatterns = {"/StaffChangePassword"})
public class StaffChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("staffId");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (newPassword.equals(confirmPassword)) {
            UserDAO userDAO = new UserDAO();
            boolean isPasswordChanged = userDAO.changePassword(username, currentPassword, newPassword);
            
            if (isPasswordChanged) {
                response.sendRedirect("staffprofile.jsp?success=Password updated successfully.");
            } else {
                response.sendRedirect("staffprofile.jsp?error=Current password is incorrect.");
            }
        } else {
            response.sendRedirect("staffprofile.jsp?error=Passwords do not match.");
        }
    }

}
