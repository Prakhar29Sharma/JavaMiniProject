package com.example.miniproject_healthcare_system;

public class timeslot {
    String date, time;
    int doctor_id, slot_id;

    public timeslot(int slot_id, String date, String time, int doctor_id) {
        this.slot_id = slot_id;
        this.date = date;
        this.time = time;
        this.doctor_id = doctor_id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
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
