<%@page import="java.util.List"%>
<%@page import="Class.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>School Management System - Dashboard</title>
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
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/StaffAttendance"><i class="bi bi-calendar2-check-fill"></i> Manage Attendance</a></li>
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

        <div style="flex: 1" class="container mt-4">               
            <div class="card mt-4"> 
                <div class="card-body">
                    <h5 class="card-title">Check Attendance for week ${week}</h5>
                    <form action="UpdateAttendance" method="post">
                        <input type="submit" class="btn btn-primary mt-3 mb-3" value="Update Attendance" id="updateAttendance">
                        <input type="hidden" value="${assignmentId}" name="assignmentId">
                        <table class="table table-striped" id="assignedRooms">
                            <thead>
                                <tr>
                                    <th style = "width: 34%">Student ID</th>
                                    <th style = "width: 33%">Student Name</th>
                                    <th style = "width: 33%">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% List<Student> students = (List<Student>) request.getAttribute("students");%>
                                <%for (Student student : students) {%>
                                <tr>
                            <td><%=student.getStudentId()%></td>
                            <td><%=student.getName()%></td>
                            <td>
                                <select name="status">
                                    <option value="<%=student.getStudentId()+"_absent"%>">Choose attendance status</option>
                                    <option value="<%=student.getStudentId()+"_present"%>">Present</option>
                                    <option value="<%=student.getStudentId()+"_late"%>">Late</option>
                                    <option value="<%=student.getStudentId()+"_absent"%>">Absent</option>
                                </select>
                            </td>
                            </tr>

                            <%}%>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>                























        <footer class="footer bg-primary text-white py-2 mt-auto">
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

