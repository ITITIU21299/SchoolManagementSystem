<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*" %>
<%@page import="DAO.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>School Management System - Schedule</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">School Management System</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/StaffPages"><i class="bi bi-house-door"></i> Dashboard</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="staffDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-people"></i> Staff
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="staffDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/StaffSalary"><i class="bi bi-cash"></i> Salary Information</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/StaffSchedule"><i class="bi bi-calendar3"></i> Schedule</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="studentDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-mortarboard"></i> Students
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="studentDropdown">
                                <li><a class="dropdown-item" href="#"><i class="bi bi-calendar2-check-fill"></i> Manage Attendance</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/StaffAssignRoom"><i class="bi bi-card-checklist"></i> Assign Sections</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-book"></i> Exams</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-building"></i> Rooms</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/StaffProfile"><i class="bi bi-person-circle"></i> Profile</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="staffDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-gear"></i> Setting
                            </a>
                            <ul class="dropdown-menu p-1" aria-labelledby="staffDropdown">
                                <table style="width: 100%; border-spacing: 5px;">
                                    <tr>
                                        <td style="width: 30px; text-align: center;">
                                            <div class="form-check form-switch" style="margin-left: 7px">
                                                <input class="form-check-input" type="checkbox" role="switch" id="darkMode">
                                            </div>
                                        </td>
                                        <td>
                                            <label class="form-check-label" for="darkMode">Dark Mode</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 30px; text-align: center;">
                                            <i class="bi bi-person-circle" style="font-size: 1.4rem;"></i>
                                        </td>
                                        <td>
                                            <a class="dropdown-item" href="staffprofile.jsp">Profile</a>
                                        </td>
                                    </tr>
                                </table>
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
            <h1>School Schedule</h1>
            <div class="row justify-content-center mt-4">
                <div class="col-md-9 mb-">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Time Table</h5>
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <button id="prevWeek" class="btn btn-primary"><i class="bi bi-chevron-left"></i> Previous Week</button>
                                <h6 id="currentWeek" class="mb-0">Week of <span id="weekStart"></span></h6>
                                <button id="nextWeek" class="btn btn-primary">Next Week <i class="bi bi-chevron-right"></i></button>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-bordered">
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
                                            List<Schedule> schedules = (List<Schedule>) request.getAttribute("schedules");
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
                                                    case "2": mon.add(sc); break;
                                                    case "3": tue.add(sc); break;
                                                    case "4": wed.add(sc); break;
                                                    case "5": thu.add(sc); break;
                                                    case "6": fri.add(sc); break;
                                                    case "7": sat.add(sc); break;
                                                    case "8": sun.add(sc); break;
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
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + mon.get(i).getSubject_name() + " </span> " + "<span>Room " + mon.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + mon.get(i).getStart_time() + " - " + mon.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");
                                                if (tue.size() > i)                                    
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + tue.get(i).getSubject_name() + " </span> " + "<span>Room " + tue.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + tue.get(i).getStart_time() + " - " + tue.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");
                                                if (wed.size() > i)                                    
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + wed.get(i).getSubject_name() + " </span> " + "<span>Room " + wed.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + wed.get(i).getStart_time() + " - " + wed.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");
                                                if (thu.size() > i)                                    
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + thu.get(i).getSubject_name() + " </span> " + "<span>Room " + thu.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + thu.get(i).getStart_time() + " - " + thu.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");                                    
                                                if (fri.size() > i)                                    
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + fri.get(i).getSubject_name() + " </span> " + "<span>Room " + fri.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + fri.get(i).getStart_time() + " - " + fri.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");                                    
                                                if (sat.size() > i)                                    
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + sat.get(i).getSubject_name() + " </span> " + "<span>Room " + sat.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + sat.get(i).getStart_time() + " - " + sat.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");
                                                if (sun.size() > i)                                    
                                                out.println("<td><div style = 'padding-left: 10px' class = 'card'> " + "<span style='color: #4A90E2; font-weight: bold;'>" + sun.get(i).getSubject_name() + " </span> " + "<span>Room " + sun.get(i).getRoom_id() + "</span>" + "<span style='color: #D0021B;'>" + sun.get(i).getStart_time() + " - " + sun.get(i).getEnd_time() + "</span></div></td>");
                                                else out.println("<td></td>");

                                                out.println("</tr>");
                                            }            
                                        %>
                                </table>
                            </div>
                        </div>
                    </div>
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
        <script src="schedule.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

