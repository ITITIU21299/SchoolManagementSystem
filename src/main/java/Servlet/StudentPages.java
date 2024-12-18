package Servlet;

import Class.*;
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
import DAO.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "StudentPages", urlPatterns = {"/StudentPages"})
public class StudentPages extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentByUsername(user.getUsername());
        request.setAttribute("student", student);

        List<Exam> exams = studentDAO.getExamsByStudentId(student.getStudentId());
        request.setAttribute("exams", exams);

        float marks = studentDAO.getMarksByStudentId(student.getStudentId());
        request.setAttribute("marks", marks);

        List<Schedule> schedules = studentDAO.getRoomScheduleByStudentId(student.getStudentId());
        session.setAttribute("schedules", schedules);
        for (Schedule sc: schedules)
            System.out.println(sc.getRoom_id());
        
        List<Fee> fees = studentDAO.getFeesByStudentId(student.getStudentId());
        session.setAttribute("fees", fees);

        RequestDispatcher dispatcher = request.getRequestDispatcher("studentpages.jsp");
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
