package com.example.miniproject_healthcare_system;

public class appointment {
    int appointmentId;
    String doctorName, patientName, date, time, paymentStatus, appointmentStatus, reason;

    public appointment(int appointmentId, String doctorName, String patientName, String date, String time, String paymentStatus, String appointmentStatus, String reason) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.paymentStatus = paymentStatus;
        this.appointmentStatus = appointmentStatus;
        this.reason = reason;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
