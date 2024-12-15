package Servlet;

import Class.Staff;
import Class.Student;
import DAO.StaffDAO;
import DAO.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "StaffProfile", urlPatterns = {"/StaffProfile"})
public class StaffProfile extends HttpServlet {

    private StaffDAO staffDAO = new StaffDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/staffprofile.jsp");
        dispatcher.forward(request, response);
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

            boolean updated = staffDAO.updateStaffProfile(staff);
            request.setAttribute("staff", staff);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/staffprofile.jsp");
            dispatcher.forward(request, response);
        }

    }

}
