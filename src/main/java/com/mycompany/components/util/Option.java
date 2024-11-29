/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.components.util;


/**
 *
 * @author An Ninh
 */
public class Option {
    public Option(){}
    public  String[] StatusOptions() {
        return new String[]{"Còn hàng", "Hết hàng", "Đang bảo hành"};
    }
    public String[] PriceOptions() {
    float[] prices = new float[]{500000, 1000000, 20000000, 3000000, 4000000, 5000000, 7000000, 8000000, 9000000};
    String[] priceStrings = new String[prices.length];
    
    for (int i = 0; i < prices.length; i++) {
        priceStrings[i] = String.format("%.0f", prices[i]);  // Chuyển float thành String, làm tròn
    }
    
    return priceStrings;
}

}
