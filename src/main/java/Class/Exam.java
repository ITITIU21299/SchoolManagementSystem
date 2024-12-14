package Class;

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
