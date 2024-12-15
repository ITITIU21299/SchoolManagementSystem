<%-- 
    Document   : studentroomschedule.jsp
    Created on : Dec 14, 2024, 8:36:35 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*" %>
<%@page import="DAO.*" %>
<%@page import="java.util.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>School Management System - Student Room Schedule</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body class="body">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">School Management System</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/StudentPages"><i class="bi bi-house-door"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentfee.jsp"><i class="bi bi-cash-coin"></i> Fee Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentexamschedule.jsp"><i class="bi bi-calendar-check"></i> Exam Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"><i class="bi bi-calendar3"></i> Room Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentfeedback.jsp"><i class="bi bi-chat-right-text"></i> Feedback</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentprofile.jsp"><i class="bi bi-person-circle"></i> Profile</a>
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
                            <a class="nav-link" href="index.html"><i class="bi bi-box-arrow-right"></i> Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h1>Room Schedule</h1>
            <div class="card mt-4">
                <div class="card-body">
                    <h5 class="card-title">Your Class Schedule</h5>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Monday</th>
                                <th>Tuesday</th>
                                <th>Wednesday</th>
                                <th>Thursday</th>
                                <th>Friday</th>
                                <th>Saturday</th>
                                <th>Sunday</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Schedule> schedules = (List<Schedule>) session.getAttribute("schedules");
                                List<Schedule> mon = (List<Schedule>) new ArrayList<Schedule>(); 
                                List<Schedule> tue = (List<Schedule>) new ArrayList<Schedule>(); 
                                List<Schedule> wed = (List<Schedule>) new ArrayList<Schedule>(); 
                                List<Schedule> thu = (List<Schedule>) new ArrayList<Schedule>(); 
                                List<Schedule> fri = (List<Schedule>) new ArrayList<Schedule>(); 
                                List<Schedule> sat = (List<Schedule>) new ArrayList<Schedule>(); 
                                List<Schedule> sun = (List<Schedule>) new ArrayList<Schedule>(); 
                                int cnt = (int) 0;
                                
                                for (Schedule sc : schedules) {
                                    String day = sc.getSchedule_date();
                                    switch (day) {
                                        case "Monday": mon.add(sc); break;
                                        case "Tuesday": tue.add(sc); break;
                                        case "Wednesday": wed.add(sc); break;
                                        case "Thursday": thu.add(sc); break;
                                        case "Friday": fri.add(sc); break;
                                        case "Saturday": sat.add(sc); break;
                                        case "Sunday": sun.add(sc); break;
                                    }
                                }    
                                cnt = Math.max(cnt, mon.size());
                                cnt = Math.max(cnt, tue.size());
                                cnt = Math.max(cnt, wed.size());
                                cnt = Math.max(cnt, thu.size());
                                cnt = Math.max(cnt, fri.size());
                                cnt = Math.max(cnt, sat.size());
                                cnt = Math.max(cnt, sun.size());
                                for (int i = (int) 0; i < cnt; i++) {
                                    out.println("<tr>");
                                    
                                    if (mon.size() > i) 
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + mon.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + mon.get(i).getStart_time() + " - " + mon.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");
                                    if (tue.size() > i)                                    
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + tue.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + tue.get(i).getStart_time() + " - " + tue.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");
                                    if (wed.size() > i)                                    
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + wed.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + wed.get(i).getStart_time() + " - " + wed.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");
                                    if (thu.size() > i)                                    
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + thu.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + thu.get(i).getStart_time() + " - " + thu.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");                                    
                                    if (fri.size() > i)                                    
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + fri.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + fri.get(i).getStart_time() + " - " + fri.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");                                    
                                    if (sat.size() > i)                                    
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + sat.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + sat.get(i).getStart_time() + " - " + sat.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");
                                    if (sun.size() > i)                                    
                                    out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + sun.get(i).getSubject_name() + " </span><br> " + "<span style='color: #D0021B;'>" + sun.get(i).getStart_time() + " - " + sun.get(i).getEnd_time() + "</span></div></td>");
                                    else out.println("<td></td>");
                                    
                                    out.println("</tr>");
                                }            
                                out.println("<tr><td>SAMPLE</td></tr>");
                                for (int i = (int) 0; i < 3; i++)  {
                                    out.println("<tr>");
                                    for (int j = (int) 0; j < 7; j++)
                                        out.println("<td><div class = 'card'> " + j + " </div></td>");
                                    out.println("</tr>");
                                }
                            %>
 
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <footer class="footer bg-primary text-white py-2 mt-auto fixed-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 mt-2 mb-2">
                        <h3 class="mb-1 mt-2">School Management System</h3>
                    </div>
                    <div class="col-md-4 mb-2">
                        <h6 class="mb-1 mt-2">Quick Links</h6>
                        <div class="small">
                            <a href="https://github.com/ITITIU21299/SchoolManagement" target="_blank" class="text-white me-2">About</a>
                            <a href="privacy.html" target="_blank" class="text-white me-2">Privacy</a>
                            <a href="terms.html" target="_blank" class="text-white">Terms</a>
                        </div>
                    </div>
                    <div class="col-md-4 mb-2">
                        <h6 class="mb-1">Contact</h6>
                        <p class="small mb-0">schoolmanagementad@gmail.com</p>
                        <p class="small mb-0">0123456789</p>
                    </div>
                </div>
                <hr class="my-2 bg-white opacity-25">
                <div class="text-center small">
                    <p class="mb-0">&copy;2024 School Management System. All rights reserved.</p>
                </div>
            </div>
        </footer>
        <script src="JavaScript/theme.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>


