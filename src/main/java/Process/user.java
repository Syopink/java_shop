/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class user {
    private String idCustomer;


    private String email;
    private String fullName;
    private String role;
    private String password;
    private String address;
    private String numberOfPhone;

    public user(String email, String fullName, String role) {
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public user(String email, String fullName, String role, String address, String numberOfPhone) {
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.address = address;
        this.numberOfPhone = numberOfPhone;
    }
    
    
    public String getIdCustomer() {
        return idCustomer;
    }
    public user(String email) {
        this.email = email;
    }

    public user() {
    }

    // Getters và Setters cho các thuộc tính
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberOfPhone(String numberOfPhone) {
        this.numberOfPhone = numberOfPhone;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getNumberOfPhone() {
        return numberOfPhone;
    }
    
}
