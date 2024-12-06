package Pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List; // Thay vì import java.awt.List, sử dụng java.util.List

public class Order {
    private int idOrder;
    private String idCustomer;
    private String idProduct;
    private String email;
    private String name;
    private String phone;
    private String address;
    private List<OrderItem> orderItems;  // Danh sách các sản phẩm trong đơn hàng
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private BigDecimal totalPrice; // Thêm trường totalPrice

    public Order(int idOrder, String idCustomer, String email, String name, String phone, String address, int status, Timestamp createdAt, Timestamp updatedAt, BigDecimal totalPrice) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalPrice = totalPrice;
    }


    // Constructor mới với totalPrice và orderItems
    public Order(int idOrder, String idCustomer, String idProduct, String email, String name, String phone, String address, List<OrderItem> orderItems, Timestamp createdAt, Timestamp updatedAt, int status, BigDecimal totalPrice) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orderItems = orderItems; // Danh sách sản phẩm trong đơn hàng
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.totalPrice = totalPrice; // Gán totalPrice
    }

    // Getter và Setter
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice; // Setter cho totalPrice
    }

    // Phương thức format thời gian
    public String getFormattedCreatedAt() {
        if (this.createdAt == null) {
            return ""; // hoặc có thể trả về một giá trị mặc định như "N/A" nếu cần
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.createdAt);
    }

    public String getFormattedUpdatedAt() {
        if (this.updatedAt == null) {
            return ""; // hoặc có thể trả về một giá trị mặc định như "N/A" nếu cần
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.updatedAt);
    }
}
