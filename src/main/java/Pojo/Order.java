package Pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Order {
    private String idOrder; // ID của đơn hàng (INT)
    private String idCustomer; // ID khách hàng (INT)
    private String name; // Tên khách hàng (NVARCHAR)
    private String email; // Email khách hàng (NVARCHAR)
    private String phone; // Số điện thoại khách hàng (NVARCHAR)
    private String address; // Địa chỉ khách hàng (NVARCHAR)
    private BigDecimal totalPrice; // Tổng giá (DECIMAL)
    private Timestamp createdAt; // Thời gian tạo (DATETIME)
    private Timestamp updatedAt; // Thời gian cập nhật (DATETIME)
    private int status; // Trạng thái đơn hàng (TINYINT)

    // Constructor
    public Order(String idOrder, String idCustomer, String name, String email, String phone, String address, 
                 BigDecimal totalPrice, Timestamp createdAt, Timestamp updatedAt, int status) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    // Getters
    public String getIdOrder() {
        return idOrder;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public int getStatus() {
        return status;
    }

    // Setters
    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Helper Methods
    public String getFormattedCreatedAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(createdAt);
    }

    public String getFormattedUpdatedAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(updatedAt);
    }
}
