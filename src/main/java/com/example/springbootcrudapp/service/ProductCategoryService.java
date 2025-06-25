package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.entity.ProductCategory;
import com.example.springbootcrudapp.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // Get all categories
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    // Get category by ID
    public Optional<ProductCategory> getCategoryById(Long id) {
        return productCategoryRepository.findById(id);
    }

    // Create a new category
    public ProductCategory createCategory(ProductCategory category) {
        // Check if category name already exists
        if (productCategoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category name already exists: " + category.getName());
        }
        
        // Check if category code already exists (if provided)
        if (category.getCategoryCode() != null && !category.getCategoryCode().isEmpty() &&
            productCategoryRepository.existsByCategoryCode(category.getCategoryCode())) {
            throw new RuntimeException("Category code already exists: " + category.getCategoryCode());
        }
        
        // Validate parent category exists if specified
        if (category.getParentCategoryId() != null) {
            if (!productCategoryRepository.existsById(category.getParentCategoryId())) {
                throw new RuntimeException("Parent category not found with id: " + category.getParentCategoryId());
            }
        }
        
        // Set creation and modification dates
        Date now = new Date();
        category.setCreatedDate(now);
        category.setLastModifiedDate(now);
        
        // Initialize product count if not set
        if (category.getProductCount() == null) {
            category.setProductCount(0);
        }
        
        return productCategoryRepository.save(category);
    }

    // Update category
    public ProductCategory updateCategory(Long id, ProductCategory categoryDetails) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        // Check if name is being changed and if it already exists
        if (!category.getName().equals(categoryDetails.getName()) && 
            productCategoryRepository.existsByName(categoryDetails.getName())) {
            throw new RuntimeException("Category name already exists: " + categoryDetails.getName());
        }
        
        // Check if category code is being changed and if it already exists
        if (categoryDetails.getCategoryCode() != null && !categoryDetails.getCategoryCode().isEmpty() &&
            !categoryDetails.getCategoryCode().equals(category.getCategoryCode()) &&
            productCategoryRepository.existsByCategoryCode(categoryDetails.getCategoryCode())) {
            throw new RuntimeException("Category code already exists: " + categoryDetails.getCategoryCode());
        }
        
        // Validate parent category exists if specified
        if (categoryDetails.getParentCategoryId() != null) {
            if (!productCategoryRepository.existsById(categoryDetails.getParentCategoryId())) {
                throw new RuntimeException("Parent category not found with id: " + categoryDetails.getParentCategoryId());
            }
            
            // Prevent circular reference (category cannot be its own parent)
            if (categoryDetails.getParentCategoryId().equals(id)) {
                throw new RuntimeException("Category cannot be its own parent");
            }
        }

        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        category.setCategoryCode(categoryDetails.getCategoryCode());
        category.setParentCategoryId(categoryDetails.getParentCategoryId());
        category.setDisplayOrder(categoryDetails.getDisplayOrder());
        category.setImageUrl(categoryDetails.getImageUrl());
        category.setIcon(categoryDetails.getIcon());
        category.setColor(categoryDetails.getColor());
        category.setProductCount(categoryDetails.getProductCount());
        category.setIsFeatured(categoryDetails.getIsFeatured());
        category.setIsVisible(categoryDetails.getIsVisible());
        category.setMetaTitle(categoryDetails.getMetaTitle());
        category.setMetaDescription(categoryDetails.getMetaDescription());
        category.setTags(categoryDetails.getTags());
        category.setActive(categoryDetails.getActive());
        
        // Update last modified date
        category.setLastModifiedDate(new Date());

        return productCategoryRepository.save(category);
    }

    // Delete category
    public void deleteCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        // Check if category has subcategories
        Long subcategoryCount = productCategoryRepository.countSubcategories(id);
        if (subcategoryCount > 0) {
            throw new RuntimeException("Cannot delete category with subcategories. Found " + subcategoryCount + " subcategories.");
        }
        
        productCategoryRepository.delete(category);
    }

    // Find category by name
    public Optional<ProductCategory> getCategoryByName(String name) {
        return productCategoryRepository.findByName(name);
    }

    // Find category by category code
    public Optional<ProductCategory> getCategoryByCode(String categoryCode) {
        return productCategoryRepository.findByCategoryCode(categoryCode);
    }

    // Search categories by name
    public List<ProductCategory> searchCategoriesByName(String name) {
        return productCategoryRepository.findByNameContainingIgnoreCase(name);
    }

    // Search categories by description
    public List<ProductCategory> searchCategoriesByDescription(String description) {
        return productCategoryRepository.findByDescriptionContainingIgnoreCase(description);
    }

    // Find categories by parent ID
    public List<ProductCategory> getCategoriesByParent(Long parentCategoryId) {
        return productCategoryRepository.findByParentCategoryId(parentCategoryId);
    }

    // Find root categories (no parent)
    public List<ProductCategory> getRootCategories() {
        return productCategoryRepository.findByParentCategoryIdIsNull();
    }

    // Find subcategories by parent ID and active status
    public List<ProductCategory> getSubcategoriesByParentAndActive(Long parentCategoryId, Boolean active) {
        return productCategoryRepository.findByParentCategoryIdAndActive(parentCategoryId, active);
    }

    // Find active root categories
    public List<ProductCategory> getActiveRootCategories() {
        return productCategoryRepository.findByParentCategoryIdIsNullAndActive(true);
    }

    // Find active categories
    public List<ProductCategory> getActiveCategories() {
        return productCategoryRepository.findByActive(true);
    }

    // Find inactive categories
    public List<ProductCategory> getInactiveCategories() {
        return productCategoryRepository.findByActive(false);
    }

    // Find visible categories
    public List<ProductCategory> getVisibleCategories() {
        return productCategoryRepository.findByIsVisible(true);
    }

    // Find hidden categories
    public List<ProductCategory> getHiddenCategories() {
        return productCategoryRepository.findByIsVisible(false);
    }

    // Find featured categories
    public List<ProductCategory> getFeaturedCategories() {
        return productCategoryRepository.findByIsFeatured(true);
    }

    // Find non-featured categories
    public List<ProductCategory> getNonFeaturedCategories() {
        return productCategoryRepository.findByIsFeatured(false);
    }

    // Find categories by active status and visibility
    public List<ProductCategory> getCategoriesByActiveAndVisible(Boolean active, Boolean isVisible) {
        return productCategoryRepository.findByActiveAndIsVisible(active, isVisible);
    }

    // Find categories by featured status and visibility
    public List<ProductCategory> getCategoriesByFeaturedAndVisible(Boolean isFeatured, Boolean isVisible) {
        return productCategoryRepository.findByIsFeaturedAndIsVisible(isFeatured, isVisible);
    }

    // Find categories with product count greater than
    public List<ProductCategory> getCategoriesWithProductCountGreaterThan(Integer productCount) {
        return productCategoryRepository.findByProductCountGreaterThan(productCount);
    }

    // Find categories with product count less than
    public List<ProductCategory> getCategoriesWithProductCountLessThan(Integer productCount) {
        return productCategoryRepository.findByProductCountLessThan(productCount);
    }

    // Find categories with no products
    public List<ProductCategory> getCategoriesWithNoProducts() {
        return productCategoryRepository.findCategoriesWithNoProducts();
    }

    // Find categories created after date
    public List<ProductCategory> getCategoriesCreatedAfter(Date date) {
        return productCategoryRepository.findCategoriesCreatedAfter(date);
    }

    // Find categories modified after date
    public List<ProductCategory> getCategoriesModifiedAfter(Date date) {
        return productCategoryRepository.findCategoriesModifiedAfter(date);
    }

    // Find categories by tag
    public List<ProductCategory> getCategoriesByTag(String tag) {
        return productCategoryRepository.findByTagsContaining(tag);
    }

    // Find categories by meta title
    public List<ProductCategory> getCategoriesByMetaTitle(String title) {
        return productCategoryRepository.findByMetaTitleContaining(title);
    }

    // Find categories by color
    public List<ProductCategory> getCategoriesByColor(String color) {
        return productCategoryRepository.findByColor(color);
    }

    // Get all categories ordered by display order
    public List<ProductCategory> getAllCategoriesOrderedByDisplayOrder() {
        return productCategoryRepository.findAllByOrderByDisplayOrderAsc();
    }

    // Get all categories ordered by name
    public List<ProductCategory> getAllCategoriesOrderedByName() {
        return productCategoryRepository.findAllByOrderByNameAsc();
    }

    // Get all categories ordered by product count (highest first)
    public List<ProductCategory> getAllCategoriesOrderedByProductCount() {
        return productCategoryRepository.findAllByOrderByProductCountDesc();
    }

    // Get all categories ordered by creation date (newest first)
    public List<ProductCategory> getAllCategoriesOrderedByCreationDate() {
        return productCategoryRepository.findAllByOrderByCreatedDateDesc();
    }

    // Get all categories ordered by last modified date (most recent first)
    public List<ProductCategory> getAllCategoriesOrderedByModificationDate() {
        return productCategoryRepository.findAllByOrderByLastModifiedDateDesc();
    }

    // Find categories by multiple criteria
    public List<ProductCategory> getCategoriesByCriteria(Long parentCategoryId, Boolean active, 
                                                        Boolean isVisible, Boolean isFeatured) {
        return productCategoryRepository.findCategoriesByCriteria(parentCategoryId, active, isVisible, isFeatured);
    }

    // Get category hierarchy (parent and children)
    public List<ProductCategory> getCategoryHierarchy(Long categoryId) {
        return productCategoryRepository.findCategoryHierarchy(categoryId);
    }

    // Count subcategories
    public Long countSubcategories(Long parentCategoryId) {
        return productCategoryRepository.countSubcategories(parentCategoryId);
    }

    // Activate category
    public ProductCategory activateCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setActive(true);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Deactivate category
    public ProductCategory deactivateCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setActive(false);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Make category visible
    public ProductCategory makeCategoryVisible(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setIsVisible(true);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Hide category
    public ProductCategory hideCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setIsVisible(false);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Feature category
    public ProductCategory featureCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setIsFeatured(true);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Unfeature category
    public ProductCategory unfeatureCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setIsFeatured(false);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Update product count
    public ProductCategory updateProductCount(Long id, Integer productCount) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setProductCount(productCount);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Update display order
    public ProductCategory updateDisplayOrder(Long id, Integer displayOrder) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setDisplayOrder(displayOrder);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }

    // Update category tags
    public ProductCategory updateCategoryTags(Long id, String tags) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setTags(tags);
        category.setLastModifiedDate(new Date());
        return productCategoryRepository.save(category);
    }
} 