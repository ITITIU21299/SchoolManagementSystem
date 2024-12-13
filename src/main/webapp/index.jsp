<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>School Management System - Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body>
        <div class="background-container">
            <img src="imgs/login-background.jpg" alt="Background" style="width: 100%; height: 100%; object-fit: cover; position: absolute; z-index: -1;">
            <div class="container">
                <div class="row justify-content-center align-items-center min-vh-100">
                    <div class="col-md-6 col-lg-4">
                        <div class="card shadow">
                            <div class="card-body">
                                <div class="text-center mb-4">
                                    <img src="/placeholder.svg?height=80&width=80" alt="School Logo" class="mb-3">
                                    <h2 class="card-title">School Management System</h2>
                                </div>
                                <form method="post" action="Login">
                                    <div class="mb-3">
                                        <label for="username" class="form-label">Username</label>
                                        <input type="text" class="form-control" id="username" name="username" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="password" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="password" name="password" required>
                                    </div>
                                    <div class="d-grid">
                                        <input type="submit" class="btn btn-primary">
                                    </div> 

                                    <% String errorMessage = (String) request.getAttribute("errorMessage"); 
                                         if (errorMessage != null) { %>
                                    <p style="color: red;"><%= errorMessage %></p>
                                    <% } %>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>