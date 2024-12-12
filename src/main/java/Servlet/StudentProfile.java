/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Class.Student;
import DAO.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
@WebServlet(name = "StudentProfile", urlPatterns = {"/StudentProfile"})
public class StudentProfile extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        if (username != null) {
            Student student = studentDAO.getStudentByUsername(username);
            if (student != null) {
                request.setAttribute("student", student);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/studentprofile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("fullName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");

        if (username != null) {
            Student student = new Student();
            student.setName(name);
            student.setDateOfBirth(dateOfBirth);
            student.setGender(gender);
            student.setEmail(email);

            boolean updated = studentDAO.updateStudentProfile(student);
            request.setAttribute("student", student);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/studentprofile.jsp");
            dispatcher.forward(request, response);
            if (updated) {
                response.sendRedirect("StudentProfile?username=" + username);
            } else {
                response.sendRedirect("StudentProfile?username=" + username + "&error=true");
            }
        }
    }
}

