/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Validate {
    //Định dạng email
    public boolean isValidEmail(String email) {
    // Định dạng email đơn giản bằng regex
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email.matches(emailRegex);
}
}
