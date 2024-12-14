<%-- 
    Document   : staffschedule
    Created on : Dec 11, 2024, 10:30:18 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <li><a class="dropdown-item" href="staffsalary.jsp"><i class="bi bi-cash"></i> Salary Information</a></li>
                                <li><a class="dropdown-item" href="staffschedule.jsp"><i class="bi bi-calendar3"></i> Schedule</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="studentDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-mortarboard"></i> Students
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="studentDropdown">
                                <li><a class="dropdown-item" href="#"><i class="bi bi-calendar2-check-fill"></i> Manage Attendance</a></li>
                                <li><a class="dropdown-item" href="#"><i class="bi bi-card-checklist"></i> Assign Sections</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-book"></i> Exams</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-building"></i> Rooms</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-journal-text"></i> Library</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="staffprofile.jsp"><i class="bi bi-person-circle"></i> Profile</a>
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
                                            <th>Time</th>
                                            <th>
                                                Monday
                                                <br>
                                                <small class="text-muted" id="date-monday"></small>
                                            </th>
                                            <th>
                                                Tuesday
                                                <br>
                                                <small class="text-muted" id="date-tuesday"></small>
                                            </th>
                                            <th>
                                                Wednesday
                                                <br>
                                                <small class="text-muted" id="date-wednesday"></small>
                                            </th>
                                            <th>
                                                Thursday
                                                <br>
                                                <small class="text-muted" id="date-thursday"></small>
                                            </th>
                                            <th>
                                                Friday
                                                <br>
                                                <small class="text-muted" id="date-friday"></small>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody id="scheduleBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Upcoming Events</h5>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Parent-Teacher Meeting
                                    <span class="badge bg-primary rounded-pill">June 15</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Science Fair
                                    <span class="badge bg-primary rounded-pill">June 20</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Sports Day
                                    <span class="badge bg-primary rounded-pill">June 25</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Schedule Changes</h5>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <strong>June 18:</strong> Math class moved to Room 201
                                </li>
                                <li class="list-group-item">
                                    <strong>June 22:</strong> Early dismissal at 2:00 PM
                                </li>
                                <li class="list-group-item">
                                    <strong>June 29:</strong> Staff development day - No classes
                                </li>
                            </ul>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="schedule.js"></script>
    </body>
</html>

