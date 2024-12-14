<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*" %>
<%@page import="DAO.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>School Management System - Student Profile</title>
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
                            <a class="nav-link" href="studentexamschedule.jsp"><i class="bi bi-calendar-check"></i> Exam Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentroomschedule.html"><i class="bi bi-calendar3"></i> Room Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="studentfeedback.jsp"><i class="bi bi-chat-right-text"></i> Feedback</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="staffprofile.jsp"><i class="bi bi-person-circle"></i> Profile</a>
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
            <h1>Staff Profile</h1>
            <div class="row mt-4">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <img src="https://via.placeholder.com/150" alt="Student Photo" class="rounded-circle mb-3">
                            <h5 class="card-title">${staff.getName()}</h5>
                            <p class="card-text">Staff ID: ${staff.getStaffId()}</p>
                            <p class="card-text">Email: ${staff.getEmail()}</p>
                            <p class="card-text">Phone: ${staff.getPhone()}</p>
                            <p class="card-text">Address: ${staff.getAddress()}</p>
                            <p class="card-text">Qualification: ${staff.getQualification()}</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#changePhotoModal">Change Photo</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Personal Information</h5>
                            <form action="StaffProfile" method="POST">
                                <input type="hidden" name="username">
                                <div class="mb-3">
                                    <label for="fullName" class="form-label">Full Name</label>
                                    <input type="text" class="form-control" id="fullName" name="fullName"  required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="phone" class="form-label">Phone number</label>
                                    <input type="text" class="form-control" id="phone" name="phone" required>
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="address" name="address" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Update Personal Information</button>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Account Settings</h5>
                            <form action="StaffChangePassword" method="post">
                                <div class="mb-3">
                                    <label for="currentPassword" class="form-label">Current Password</label>
                                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                                </div>
                                <div class="mb-3">
                                    <label for="newPassword" class="form-label">New Password</label>
                                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                                </div>
                                <div class="mb-3">
                                    <label for="confirmPassword" class="form-label">Confirm New Password</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Change Password</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="changePhotoModal" tabindex="-1" aria-labelledby="changePhotoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changePhotoModalLabel">Change Profile Photo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label for="profilePhoto" class="form-label">Select a new profile photo</label>
                                <input type="file" class="form-control" id="profilePhoto" accept="image/*" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Upload Photo</button>
                    </div>
                </div>
            </div>
        </div>

        <footer class="bg-primary text-white py-2 mt-auto ">
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

