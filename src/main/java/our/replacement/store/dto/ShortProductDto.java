package our.replacement.store.dto;

import java.time.LocalDateTime;

public class ShortProductDto {

    private Long productId;

    private Long sellerId;

    private Long categoryId;

    private String categoryName;

    private Long imageId;

    private String imageUrl;

    private String name;

    private Double price;

    private String customerName;

    private LocalDateTime dealTimestamp;

    public ShortProductDto() {
    }

    public ShortProductDto(Long productId, Long sellerId, Long categoryId, Long imageId, String name,
                           Double price, String categoryName, String imageUrl, String customerName, LocalDateTime dealTimestamp) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.imageId = imageId;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
        this.customerName = customerName;
        this.dealTimestamp = dealTimestamp;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }
}
