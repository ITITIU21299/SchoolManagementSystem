/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author admin
 */
public class Exam {
    private String subject;
    private String date;
    private String startTime;
    private String endTime;
    private String room;
    
    public Exam (String subject, String date, String startTime, String endTime, String room) {
        this.subject = subject;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        
    }

    public String getSubject() {
        return subject;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public String getRoomNumber() {
        return room;
    }    
}
