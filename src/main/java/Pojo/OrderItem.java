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
public class OrderItem {
    private String idOrderItem;
    private String idOrder;
    private String idProduct;
    private int quantity;
    private BigDecimal price;

    public OrderItem(String idOrderItem, String idOrder, String idProduct, int quantity, BigDecimal price) {
        this.idOrderItem = idOrderItem;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(String idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
