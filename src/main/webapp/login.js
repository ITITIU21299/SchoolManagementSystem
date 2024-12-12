document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value.trim();

        if (username === '') {
            alert('Please enter a username');
            return;
        }

        if (password === '') {
            alert('Please enter a password');
            return;
        }

        console.log('Login attempt:', { username, password });
        window.location.href = 'pages.html';

    });
});

