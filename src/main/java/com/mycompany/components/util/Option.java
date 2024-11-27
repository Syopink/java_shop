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
    public  String[] PriceOptions() {
        return new String[]{"0.10 - 500000.10", "500000.10 - 1000000.10", "1000000.10 - 4000000.10","4000000.10 - 8000000.10","10000000.10"};
        }
}
