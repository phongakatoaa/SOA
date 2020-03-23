package model;

import com.google.gson.JsonObject;

public class Student {
    private String fullName;
    private String className;
    private String studentId;
    private String phoneNumber;
    private String email;
    private String address;

    public Student() {

    }

    public Student(JsonObject item) {
        this.studentId = item.get("studentId").getAsString();
        this.fullName = item.get("fullName").getAsString();
        this.address = item.get("address").getAsString();
        this.className = item.get("className").getAsString();
        this.email = item.get("email").getAsString();
        this.phoneNumber = item.get("phoneNumber").getAsString();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void printInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Full name: " + fullName);
        System.out.println("Class name: " + className);
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }
}
