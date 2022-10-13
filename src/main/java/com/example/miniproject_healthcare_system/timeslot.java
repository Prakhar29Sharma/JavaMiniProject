package com.example.miniproject_healthcare_system;

public class timeslot {
    String date, time;
    int doctor_id;

    public timeslot(String date, String time, int doctor_id) {
        this.date = date;
        this.time = time;
        this.doctor_id = doctor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
}
