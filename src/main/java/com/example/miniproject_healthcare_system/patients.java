package com.example.miniproject_healthcare_system;

public class patients {
    int patient_id;
    String firstName, lastName,phone_o,email,city,cityArea,gender;

    public patients(int patient_id, String firstName, String lastName, String phone_o, String email, String city, String cityArea, String gender) {
        this.patient_id = patient_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone_o = phone_o;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone_o() {
        return phone_o;
    }

    public void setPhone_o(String phone_o) {
        this.phone_o = phone_o;
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
