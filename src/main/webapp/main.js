document.addEventListener('DOMContentLoaded', function() {
    // Add active class to current nav item
    const currentLocation = location.href;
    const navItems = document.querySelectorAll('.navbar-nav .nav-link');
    navItems.forEach(item => {
        if (item.href === currentLocation) {
            item.classList.add('active');
        }
    });

    // Simulate loading data (replace with actual API calls in a real application)
    function loadDashboardData() {
        // This is a placeholder. In a real application, you'd fetch this data from your backend.
        const totalStudents = 1234;
        const totalStaff = 56;
        const totalClasses = 32;

        document.querySelector('.card-text:nth-of-type(1)').textContent = totalStudents.toLocaleString();
        document.querySelector('.card-text:nth-of-type(2)').textContent = totalStaff.toLocaleString();
        document.querySelector('.card-text:nth-of-type(3)').textContent = totalClasses.toLocaleString();
    }

    loadDashboardData();
});

