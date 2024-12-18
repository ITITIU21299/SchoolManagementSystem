<%-- 
    Document   : studentexamschedule
    Created on : Dec 11, 2024, 3:58:21 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*" %>
<%@page import="DAO.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>School Management System - Student Exam Schedule</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body>
        <% Student student = (Student) session.getAttribute("student"); %>
        <% StudentDAO studentDAO = (StudentDAO) session.getAttribute("studentDAO"); %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">School Management System</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/StudentPages"><i class="bi bi-house-door"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentfee.jsp"><i class="bi bi-cash-coin"></i> Fee Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"><i class="bi bi-calendar-check"></i> Exam Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/StudentSchedule"><i class="bi bi-calendar3"></i> Room Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/StudentAttendance"><i class="bi bi-calendar-check"></i> Attendance</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentfeedback.jsp"><i class="bi bi-chat-right-text"></i> Feedback</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="staffDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-gear"></i> Setting
                            </a>
                            <ul class="dropdown-menu p-2 " aria-labelledby="staffDropdown">
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" role="switch" id="darkMode">
                                    <label class="form-check-label" for="flexSwitchCheckDefault ">Dark Mode</label>
                                </div>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp"><i class="bi bi-box-arrow-right"></i> Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h1>Exam Schedule for ${student.getName()}</h1>
            <div class="card mt-4">
                <div class="card-body">
                    <h5 class="card-title">Upcoming Exams</h5>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Subject</th>
                                <th>Date</th>
                                <th>Time</th>
                                <th>Room</th>
                            </tr>
                        </thead>
                        <tbody>                        
                            <%
                                List<Exam> exams = (List<Exam>) session.getAttribute("exams");
                                if (exams != null) {
                                    for (Exam exam : exams) {
                            %>
                            <tr>
                                <td><%= exam.getSubject() != null ? exam.getSubject() : "N/A"%></td>
                                <td><%= exam.getDate() != null ? exam.getDate() : "N/A"%></td>
                                <td><%= exam.getStartTime() != null ? exam.getStartTime() : "N/A"%> - 
                                    <%= exam.getEndTime() != null ? exam.getEndTime() : "N/A"%></td>
                                <td><%= exam.getRoomNumber() != null ? exam.getRoomNumber() : "N/A"%></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="main.js"></script>
        <footer class="bg-primary text-white py-2 mt-auto fixed-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 mb-2">
                        <h6 class="mb-1">School Management System</h6>
                        <p class="small mb-0">Empowering education through technology</p>
                    </div>
                    <div class="col-md-4 mb-2">
                        <h6 class="mb-1">Quick Links</h6>
                        <div class="small">
                            <a href="#" class="text-white me-2">About</a>
                            <a href="#" class="text-white me-2">Privacy</a>
                            <a href="#" class="text-white">Terms</a>
                        </div>
                    </div>
                    <div class="col-md-4 mb-2">
                        <h6 class="mb-1">Contact</h6>
                        <p class="small mb-0">info@schoolmanagementsystem.com</p>
                        <p class="small mb-0">(123) 456-7890</p>
                    </div>
                </div>
                <hr class="my-2 bg-white opacity-25">
                <div class="text-center small">
                    <p class="mb-0">&copy;2024 School Management System. All rights reserved.</p>
                </div>
            </div>
        </footer>
        <script src="JavaScript/theme.js"></script>
    </body>
</html>

