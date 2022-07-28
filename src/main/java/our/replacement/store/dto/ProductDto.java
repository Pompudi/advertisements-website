package our.replacement.store.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ProductDto {

    private Long productId;

    private Long sellerId;

    private Long categoryId;

    private String categoryName;

    private Long imageId;

    private String imageUrl;

    private Long customerId;

    @NotNull
    @NotBlank(message = "Наименование не должно быть пустым")
    @Size(min = 1, max = 128)
    private String name;

    @PositiveOrZero(message = "Цена должна быть больше или равна 0")
    private Double price;

    @NotNull
    @NotBlank(message = "Адрес не должен быть пустым")
    @Size(min = 1, max = 512)
    private String address;

    @NotNull
    @NotBlank(message = "Описание не должно быть пустым")
    @Size(min = 1, max = 8192)
    private String description;

    private Boolean active;

    private LocalDateTime createTimestamp;

    private LocalDateTime dealTimestamp;

    private String customerName;

    public ProductDto() {
    }

    public ProductDto(Long productId, Long sellerId, Long categoryId, Long imageId, Long customerId,
                      String name, Double price, String address, Boolean active, LocalDateTime createTimestamp,
                      LocalDateTime dealTimestamp, String categoryName, String imageUrl, String description, String customerName) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.imageId = imageId;
        this.customerId = customerId;
        this.name = name;
        this.price = price;
        this.address = address;
        this.active = active;
        this.createTimestamp = createTimestamp;
        this.dealTimestamp = dealTimestamp;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.customerName = customerName;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}