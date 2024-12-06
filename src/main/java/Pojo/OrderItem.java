package Pojo;
import java.math.BigDecimal;

public class OrderItem {
    private int idProduct;
    private String productName;
    private int quantity;
    private BigDecimal price;

    // Constructor, Getter và Setter
    public OrderItem(int idProduct, String productName, int quantity, BigDecimal price) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
