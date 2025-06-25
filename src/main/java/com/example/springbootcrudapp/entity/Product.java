package com.example.springbootcrudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 200, message = "Product name must be between 2 and 200 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    @Column(name = "description")
    private String description;

    @Size(max = 2000, message = "Long description must be less than 2000 characters")
    @Column(name = "long_description")
    private String longDescription;

    @Pattern(regexp = "^[A-Z0-9_-]{3,50}$", message = "SKU must be 3-50 characters, uppercase letters, numbers, hyphens and underscores only")
    @Column(name = "sku", unique = true)
    private String sku;

    @Size(max = 100, message = "Brand must be less than 100 characters")
    @Column(name = "brand")
    private String brand;

    @Size(max = 100, message = "Model must be less than 100 characters")
    @Column(name = "model")
    private String model;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 integer digits and 2 decimal places")
    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "Cost must be greater than or equal to 0")
    @Digits(integer = 10, fraction = 2, message = "Cost must have at most 10 integer digits and 2 decimal places")
    @Column(name = "cost", precision = 12, scale = 2)
    private BigDecimal cost;

    @DecimalMin(value = "0.0", message = "Sale price must be greater than or equal to 0")
    @Digits(integer = 10, fraction = 2, message = "Sale price must have at most 10 integer digits and 2 decimal places")
    @Column(name = "sale_price", precision = 12, scale = 2)
    private BigDecimal salePrice;

    @Min(value = 0, message = "Stock quantity must be positive")
    @Column(name = "stock_quantity")
    private Integer stockQuantity = 0;

    @Min(value = 0, message = "Minimum stock level must be positive")
    @Column(name = "min_stock_level")
    private Integer minStockLevel = 0;

    @Min(value = 0, message = "Maximum stock level must be positive")
    @Column(name = "max_stock_level")
    private Integer maxStockLevel;

    @Column(name = "category_id")
    private Long categoryId;

    @DecimalMin(value = "0.0", message = "Weight must be positive")
    @Column(name = "weight")
    private Double weight;

    @Size(max = 50, message = "Weight unit must be less than 50 characters")
    @Column(name = "weight_unit")
    private String weightUnit; // kg, lbs, g, oz

    @Size(max = 100, message = "Dimensions must be less than 100 characters")
    @Column(name = "dimensions")
    private String dimensions; // L x W x H

    @Size(max = 50, message = "Color must be less than 50 characters")
    @Column(name = "color")
    private String color;

    @Size(max = 50, message = "Size must be less than 50 characters")
    @Column(name = "size")
    private String size;

    @Size(max = 500, message = "Image URL must be less than 500 characters")
    @Column(name = "image_url")
    private String imageUrl;

    @Size(max = 1000, message = "Image gallery must be less than 1000 characters")
    @Column(name = "image_gallery")
    private String imageGallery; // Comma-separated URLs

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "is_digital")
    private Boolean isDigital = false;

    @Column(name = "requires_shipping")
    private Boolean requiresShipping = true;

    @Column(name = "is_taxable")
    private Boolean isTaxable = true;

    @Column(name = "track_inventory")
    private Boolean trackInventory = true;

    @Column(name = "allow_backorder")
    private Boolean allowBackorder = false;

    @DecimalMin(value = "0.0", message = "Rating must be between 0 and 5")
    @DecimalMax(value = "5.0", message = "Rating must be between 0 and 5")
    @Column(name = "rating")
    private Double rating = 0.0;

    @Min(value = 0, message = "Review count must be positive")
    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @Min(value = 0, message = "View count must be positive")
    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Min(value = 0, message = "Sales count must be positive")
    @Column(name = "sales_count")
    private Integer salesCount = 0;

    @Size(max = 200, message = "Meta title must be less than 200 characters")
    @Column(name = "meta_title")
    private String metaTitle; // SEO meta title

    @Size(max = 500, message = "Meta description must be less than 500 characters")
    @Column(name = "meta_description")
    private String metaDescription; // SEO meta description

    @Size(max = 200, message = "Tags must be less than 200 characters")
    @Column(name = "tags")
    private String tags; // Comma-separated tags

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status = ProductStatus.DRAFT;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastModifiedDate;

    @Column(name = "published_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date publishedDate;

    @Column(name = "active")
    private Boolean active = true;

    // Enum for product status
    public enum ProductStatus {
        DRAFT, PUBLISHED, ARCHIVED, OUT_OF_STOCK, DISCONTINUED
    }

    // Default constructor
    public Product() {
        this.createdDate = new java.util.Date();
        this.lastModifiedDate = new java.util.Date();
    }

    // Constructor with parameters
    public Product(String name, String description, String sku, String brand, String model,
                  BigDecimal price, BigDecimal cost, Integer stockQuantity, Long categoryId,
                  Double weight, String weightUnit, String dimensions, String color, String size,
                  String imageUrl, Boolean isFeatured, Boolean isDigital, Boolean requiresShipping,
                  Boolean isTaxable, Boolean trackInventory, String metaTitle, String metaDescription,
                  String tags, ProductStatus status, Boolean active) {
        this();
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.cost = cost;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.dimensions = dimensions;
        this.color = color;
        this.size = size;
        this.imageUrl = imageUrl;
        this.isFeatured = isFeatured;
        this.isDigital = isDigital;
        this.requiresShipping = requiresShipping;
        this.isTaxable = isTaxable;
        this.trackInventory = trackInventory;
        this.metaTitle = metaTitle;
        this.metaDescription = metaDescription;
        this.tags = tags;
        this.status = status;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Integer minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Integer getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(Integer maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(String imageGallery) {
        this.imageGallery = imageGallery;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigital(Boolean isDigital) {
        this.isDigital = isDigital;
    }

    public Boolean getRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(Boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public Boolean getIsTaxable() {
        return isTaxable;
    }

    public void setIsTaxable(Boolean isTaxable) {
        this.isTaxable = isTaxable;
    }

    public Boolean getTrackInventory() {
        return trackInventory;
    }

    public void setTrackInventory(Boolean trackInventory) {
        this.trackInventory = trackInventory;
    }

    public Boolean getAllowBackorder() {
        return allowBackorder;
    }

    public void setAllowBackorder(Boolean allowBackorder) {
        this.allowBackorder = allowBackorder;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.util.Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public java.util.Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(java.util.Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", salePrice=" + salePrice +
                ", stockQuantity=" + stockQuantity +
                ", categoryId=" + categoryId +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", isFeatured=" + isFeatured +
                ", isDigital=" + isDigital +
                ", rating=" + rating +
                ", status=" + status +
                ", active=" + active +
                '}';
    }
} 