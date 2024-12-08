/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.math.BigDecimal;

/**
 *
 * @author An Ninh
 */
public class CartProduct {
    private String idCustomer;
    private String idProduct;
    private String idCartProduct;
    private String nameProduct;
    private int quantity;
    private BigDecimal totalPrice;
    private String category;
    private String status;
    private String thumbnail;
    
     public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdCartProduct() {
        return idCartProduct;
    }

    public void setIdCartProduct(String idCartProduct) {
        this.idCartProduct = idCartProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public BigDecimal getTotalPrice(){
        return totalPrice.multiply(BigDecimal.valueOf(quantity));

    }
    
    public void setTotalPrice(BigDecimal totalPrice){
        this.totalPrice=totalPrice;
    }
}
