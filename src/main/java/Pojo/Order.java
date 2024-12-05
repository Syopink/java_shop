/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Order {
    private String idOrder;
    private String idCustomer;
    private String idProduct;
    private String email;
    private String name;
    private String phone;
    private String address;
    private String item;
    private int isApproved;
    private Timestamp createdAt;

    public Order(String idOrder, String idCustomer, String idProduct, String email, String name, String phone, String address, String item, Timestamp createdAt,int isApproved) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.item = item;
        this.createdAt = createdAt;
        this.idOrder = idOrder;
        this.isApproved=isApproved;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getItem() {
        return item;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public int getIsApproved() {
        return isApproved;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
    
      public String getFormattedCreatedAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(createdAt);
    }
      
      public int setIsApproved(int isApproved){
         return this.isApproved= isApproved;
      }
    
}
