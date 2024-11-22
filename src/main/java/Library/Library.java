/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;
import java.util.Random;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Library {
    public String generateVerificationCode() {
    Random rand = new Random();
    return String.format("%04d", rand.nextInt(10000));
}
}
