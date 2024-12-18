document.addEventListener('DOMContentLoaded', function () {
    let currentDate = new Date();
    const prevWeekBtn = document.getElementById('prevWeek');
    const nextWeekBtn = document.getElementById('nextWeek');
    const weekStartSpan = document.getElementById('weekStart');
    const scheduleBody = document.getElementById('scheduleBody');
    const upcomingEvents = document.getElementById('upcomingEvents');
    const scheduleChanges = document.getElementById('scheduleChanges');

    function updateWeekDisplay() {
        const weekStart = new Date(currentDate);
        weekStart.setDate(currentDate.getDate() - currentDate.getDay() + 1);
        weekStartSpan.textContent = weekStart.toDateString();

        const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday'];
        days.forEach((day, index) => {
            const date = new Date(weekStart);
            date.setDate(date.getDate() + index);
            document.getElementById(`date-${day}`).textContent = date.toLocaleDateString();
        });
    }

    function updateSchedule() {
        const scheduleData = [
            {time: '8:00 - 9:00', monday: 'Math', tuesday: 'English', wednesday: 'Science', thursday: 'History', friday: 'Art'},
            {time: '9:00 - 10:00', monday: 'English', tuesday: 'Science', wednesday: 'Math', thursday: 'Geography', friday: 'Music'},
            {time: '10:00 - 10:15', monday: 'Break', tuesday: 'Break', wednesday: 'Break', thursday: 'Break', friday: 'Break'},
            {time: '10:15 - 11:15', monday: 'Science', tuesday: 'History', wednesday: 'English', thursday: 'Math', friday: 'PE'},
            {time: '11:15 - 12:15', monday: 'History', tuesday: 'Math', wednesday: 'Geography', thursday: 'Science', friday: 'English'}
        ];

        scheduleBody.innerHTML = '';
        scheduleData.forEach(row => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${row.time}</td>
                <td>${row.monday}</td>
                <td>${row.tuesday}</td>
                <td>${row.wednesday}</td>
                <td>${row.thursday}</td>
                <td>${row.friday}</td>
            `;
            scheduleBody.appendChild(tr);
        });
    }

    function updateEvents() {
        const events = [
            {name: 'Parent-Teacher Meeting', date: 'June 15'},
            {name: 'Science Fair', date: 'June 20'},
            {name: 'Sports Day', date: 'June 25'}
        ];

        upcomingEvents.innerHTML = '';
        events.forEach(event => {
            const li = document.createElement('li');
            li.className = 'list-group-item d-flex justify-content-between align-items-center';
            li.innerHTML = `
                ${event.name}
                <span class="badge bg-primary rounded-pill">${event.date}</span>
            `;
            upcomingEvents.appendChild(li);
        });
    }

    function updateChanges() {
        const changes = [
            {date: 'June 18', description: 'Math class moved to Room 201'},
            {date: 'June 22', description: 'Early dismissal at 2:00 PM'},
            {date: 'June 29', description: 'Staff development day - No classes'}
        ];

        scheduleChanges.innerHTML = '';
        changes.forEach(change => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.innerHTML = `<strong>${change.date}:</strong> ${change.description}`;
            scheduleChanges.appendChild(li);
        });
    }

    function updateAll() {
        updateWeekDisplay();
        updateSchedule();
        updateEvents();
        updateChanges();
    }

    prevWeekBtn.addEventListener('click', function () {
        currentDate.setDate(currentDate.getDate() - 7);
        updateAll();
    });

    nextWeekBtn.addEventListener('click', function () {
        currentDate.setDate(currentDate.getDate() + 7);
        updateAll();
    });

    updateAll();


});

//function weekDisplay(w) {
//    const weekStartSpan = document.getElementById('weekStart');    
//    const weekStart = new Date(currentDate);
//    weekStart.setDate(currentDate.getDate() - currentDate.getDay() + 1);
//    weekStartSpan.textContent = weekStart.toDateString();
//
//    const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday'];
//    days.forEach((day, index) => {
//        const date = new Date(weekStart);
//        date.setDate(date.getDate() + index);
//        document.getElementById(`date-${day}`).textContent = date.toLocaleDateString();
//    });
//}    