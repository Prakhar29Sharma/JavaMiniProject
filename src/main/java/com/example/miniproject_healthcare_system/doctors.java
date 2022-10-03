package com.example.miniproject_healthcare_system;

public class doctors {

    int id;
    String fname, lname, qualification, specialization, phno, city;

    public doctors(int id, String fname, String lname, String qualification, String specialization, String phno, String city) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.qualification = qualification;
        this.specialization = specialization;
        this.phno = phno;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getQualification() {
        return qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhno() {
        return phno;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
