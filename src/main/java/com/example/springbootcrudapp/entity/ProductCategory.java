package com.example.springbootcrudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;

    @Pattern(regexp = "^[A-Z0-9_]{2,20}$", message = "Category code must be 2-20 characters, uppercase letters, numbers, and underscores only")
    @Column(name = "category_code", unique = true)
    private String categoryCode;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    @Min(value = 0, message = "Display order must be positive")
    @Column(name = "display_order")
    private Integer displayOrder = 0;

    @Size(max = 200, message = "Image URL must be less than 200 characters")
    @Column(name = "image_url")
    private String imageUrl;

    @Size(max = 200, message = "Icon must be less than 200 characters")
    @Column(name = "icon")
    private String icon;

    @Size(max = 50, message = "Color must be less than 50 characters")
    @Column(name = "color")
    private String color; // Hex color code for UI

    @Min(value = 0, message = "Product count must be positive")
    @Column(name = "product_count")
    private Integer productCount = 0;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "is_visible")
    private Boolean isVisible = true;

    @Size(max = 200, message = "Meta title must be less than 200 characters")
    @Column(name = "meta_title")
    private String metaTitle; // SEO meta title

    @Size(max = 500, message = "Meta description must be less than 500 characters")
    @Column(name = "meta_description")
    private String metaDescription; // SEO meta description

    @Size(max = 200, message = "Tags must be less than 200 characters")
    @Column(name = "tags")
    private String tags; // Comma-separated tags

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastModifiedDate;

    @Column(name = "active")
    private Boolean active = true;

    // Default constructor
    public ProductCategory() {
        this.createdDate = new java.util.Date();
        this.lastModifiedDate = new java.util.Date();
    }

    // Constructor with parameters
    public ProductCategory(String name, String description, String categoryCode, Long parentCategoryId,
                          Integer displayOrder, String imageUrl, String icon, String color,
                          Boolean isFeatured, Boolean isVisible, String metaTitle, String metaDescription,
                          String tags, Boolean active) {
        this();
        this.name = name;
        this.description = description;
        this.categoryCode = categoryCode;
        this.parentCategoryId = parentCategoryId;
        this.displayOrder = displayOrder;
        this.imageUrl = imageUrl;
        this.icon = icon;
        this.color = color;
        this.isFeatured = isFeatured;
        this.isVisible = isVisible;
        this.metaTitle = metaTitle;
        this.metaDescription = metaDescription;
        this.tags = tags;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                ", displayOrder=" + displayOrder +
                ", imageUrl='" + imageUrl + '\'' +
                ", icon='" + icon + '\'' +
                ", color='" + color + '\'' +
                ", productCount=" + productCount +
                ", isFeatured=" + isFeatured +
                ", isVisible=" + isVisible +
                ", metaTitle='" + metaTitle + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", tags='" + tags + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", active=" + active +
                '}';
    }
} 