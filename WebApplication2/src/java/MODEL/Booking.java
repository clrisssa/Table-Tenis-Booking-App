/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author Clarissa Poedjiono
 */
public class Booking {
    private String username;
    private String date;
    private String startTime;
    private String endTime;
    
    public Booking(String username, String date, String startTime, String endTime){
        this.username = username;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
}
