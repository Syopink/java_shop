package Pojo;

import java.util.Date;

public class Product {
    private int idProduct;
    private String idCate;
    private String categoryTitle;  // Thêm thuộc tính để lưu title của danh mục
    private String name;
    private String thumbnail;
    private String descriptions;
    private double price;
    private String status;
    private boolean featured;
    private String promotion;
    private String warranty;
    private String accessories;
    private Date createdAt;
    private Date updatedAt;

       // Constructor không tham số
    public Product() {
    }

    // Constructor không có categoryTitle
    public Product(String idCate, String name, String thumbnail, String descriptions, double price, String status) {
        this.idCate = idCate;
        this.name = name;
        this.thumbnail = thumbnail;
        this.descriptions = descriptions;
        this.price = price;
        this.status = status;
    }

    // Constructor đầy đủ
    public Product(int idProduct, String idCate, String categoryTitle, String name, String thumbnail, String descriptions,
                   double price, String status, boolean featured, String promotion, String warranty,
                   String accessories, Date createdAt, Date updatedAt) {
        this.idProduct = idProduct;
        this.idCate = idCate;
        this.categoryTitle = categoryTitle;  // Gán giá trị title cho categoryTitle
        this.name = name;
        this.thumbnail = thumbnail;
        this.descriptions = descriptions;
        this.price = price;
        this.status = status;
        this.featured = featured;
        this.promotion = promotion;
        this.warranty = warranty;
        this.accessories = accessories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method để hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", idCate='" + idCate + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +  // Hiển thị categoryTitle
                ", name='" + name + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", featured=" + featured +
                ", promotion='" + promotion + '\'' +
                ", warranty=" + warranty +
                ", accessories='" + accessories + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
