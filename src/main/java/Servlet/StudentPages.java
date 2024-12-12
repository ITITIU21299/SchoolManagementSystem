/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
/**
 *
 * @author nguye
 */
@WebServlet(name = "StudentPages", urlPatterns = {"/StudentPages"})
public class StudentPages extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return;
        }
        String username = (String) session.getAttribute("username");
        if (username == null){
            response.sendRedirect(request.getContextPath()+"/index.html");
            return;
        }
        
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentByUsername(username);
        session.setAttribute("student", student);
        
        List<Exam> exams  = studentDAO.getExamsByStudentId(student.getStudentId());
        session.setAttribute("exams", exams);
        
        List<Fee> fees = studentDAO.getFeesByStudentId(student.getStudentId());
        session.setAttribute("fees", fees);
        
        float marks = studentDAO.getMarksByStudentId(student.getStudentId());
        session.setAttribute("marks", marks);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentpages.jsp");
        dispatcher.forward(request, response);
     }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
