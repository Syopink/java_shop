/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Customer {
    private String idCustomer;
    private String email;
    private String fullName;
    private String numberOfPhone;
    private String address;
    private String role;

    // Constructor, getters và setters
    public Customer(String idCustomer, String email, String fullName, String numberOfPhone, String address, String role) {
        this.idCustomer = idCustomer;
        this.email = email;
        this.fullName = fullName;
        this.numberOfPhone = numberOfPhone;
        this.address = address;
        this.role = role;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNumberOfPhone(String numberOfPhone) {
        this.numberOfPhone = numberOfPhone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return idCustomer;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNumberOfPhone() {
        return numberOfPhone;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
}
