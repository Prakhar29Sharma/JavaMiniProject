package com.example.miniproject_healthcare_system;

import javafx.collections.ObservableList;

public class patients {
    int patient_id;
    String Name ,ph_no,email,city,cityArea,gender;



    public patients(int patient_id, String name, String ph_no, String email, String city, String cityArea, String gender) {
        this.patient_id = patient_id;
        Name = name;
        this.ph_no = ph_no;
        this.email = email;
        this.city = city;
        this.cityArea = cityArea;
        this.gender = gender;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
