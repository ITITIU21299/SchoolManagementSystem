/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author admin
 */
public class Schedule {

    private String room_id;
    private String subject_name;
    private String schedule_date;
    private String start_time;
    private String end_time;
    String[] weekDay = {null, "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public Schedule() {
    }

    public Schedule(String room_id, String subject_name, String schedule_date, String start_time, String end_time) {
        this.room_id = room_id;
        this.subject_name = subject_name;
        this.schedule_date = schedule_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSchedule_date() {
        return weekDay[Integer.parseInt(schedule_date)];
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

}
