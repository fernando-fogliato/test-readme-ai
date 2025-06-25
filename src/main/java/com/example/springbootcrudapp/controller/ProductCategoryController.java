package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.entity.ProductCategory;
import com.example.springbootcrudapp.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    // Get all categories
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Long id) {
        Optional<ProductCategory> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Create new category
    @PostMapping
    public ResponseEntity<ProductCategory> createCategory(@Valid @RequestBody ProductCategory category) {
        try {
            ProductCategory createdCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateCategory(@PathVariable Long id, 
                                                         @Valid @RequestBody ProductCategory categoryDetails) {
        try {
            ProductCategory updatedCategory = categoryService.updateCategory(id, categoryDetails);
            return ResponseEntity.ok(updatedCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Find category by name
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCategory> getCategoryByName(@PathVariable String name) {
        Optional<ProductCategory> category = categoryService.getCategoryByName(name);
        return category.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Find category by category code
    @GetMapping("/code/{categoryCode}")
    public ResponseEntity<ProductCategory> getCategoryByCode(@PathVariable String categoryCode) {
        Optional<ProductCategory> category = categoryService.getCategoryByCode(categoryCode);
        return category.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Search categories by name
    @GetMapping("/search/name")
    public ResponseEntity<List<ProductCategory>> searchCategoriesByName(@RequestParam String name) {
        List<ProductCategory> categories = categoryService.searchCategoriesByName(name);
        return ResponseEntity.ok(categories);
    }

    // Search categories by description
    @GetMapping("/search/description")
    public ResponseEntity<List<ProductCategory>> searchCategoriesByDescription(@RequestParam String description) {
        List<ProductCategory> categories = categoryService.searchCategoriesByDescription(description);
        return ResponseEntity.ok(categories);
    }

    // Get categories by parent ID
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<ProductCategory>> getCategoriesByParent(@PathVariable Long parentId) {
        List<ProductCategory> categories = categoryService.getCategoriesByParent(parentId);
        return ResponseEntity.ok(categories);
    }

    // Get root categories (no parent)
    @GetMapping("/root")
    public ResponseEntity<List<ProductCategory>> getRootCategories() {
        List<ProductCategory> categories = categoryService.getRootCategories();
        return ResponseEntity.ok(categories);
    }

    // Get subcategories by parent ID and active status
    @GetMapping("/parent/{parentId}/active/{active}")
    public ResponseEntity<List<ProductCategory>> getSubcategoriesByParentAndActive(@PathVariable Long parentId, 
                                                                                  @PathVariable Boolean active) {
        List<ProductCategory> categories = categoryService.getSubcategoriesByParentAndActive(parentId, active);
        return ResponseEntity.ok(categories);
    }

    // Get active root categories
    @GetMapping("/root/active")
    public ResponseEntity<List<ProductCategory>> getActiveRootCategories() {
        List<ProductCategory> categories = categoryService.getActiveRootCategories();
        return ResponseEntity.ok(categories);
    }

    // Get active categories
    @GetMapping("/active")
    public ResponseEntity<List<ProductCategory>> getActiveCategories() {
        List<ProductCategory> categories = categoryService.getActiveCategories();
        return ResponseEntity.ok(categories);
    }

    // Get inactive categories
    @GetMapping("/inactive")
    public ResponseEntity<List<ProductCategory>> getInactiveCategories() {
        List<ProductCategory> categories = categoryService.getInactiveCategories();
        return ResponseEntity.ok(categories);
    }

    // Get visible categories
    @GetMapping("/visible")
    public ResponseEntity<List<ProductCategory>> getVisibleCategories() {
        List<ProductCategory> categories = categoryService.getVisibleCategories();
        return ResponseEntity.ok(categories);
    }

    // Get hidden categories
    @GetMapping("/hidden")
    public ResponseEntity<List<ProductCategory>> getHiddenCategories() {
        List<ProductCategory> categories = categoryService.getHiddenCategories();
        return ResponseEntity.ok(categories);
    }

    // Get featured categories
    @GetMapping("/featured")
    public ResponseEntity<List<ProductCategory>> getFeaturedCategories() {
        List<ProductCategory> categories = categoryService.getFeaturedCategories();
        return ResponseEntity.ok(categories);
    }

    // Get non-featured categories
    @GetMapping("/non-featured")
    public ResponseEntity<List<ProductCategory>> getNonFeaturedCategories() {
        List<ProductCategory> categories = categoryService.getNonFeaturedCategories();
        return ResponseEntity.ok(categories);
    }

    // Get categories by active status and visibility
    @GetMapping("/active/{active}/visible/{visible}")
    public ResponseEntity<List<ProductCategory>> getCategoriesByActiveAndVisible(@PathVariable Boolean active, 
                                                                                @PathVariable Boolean visible) {
        List<ProductCategory> categories = categoryService.getCategoriesByActiveAndVisible(active, visible);
        return ResponseEntity.ok(categories);
    }

    // Get categories by featured status and visibility
    @GetMapping("/featured/{featured}/visible/{visible}")
    public ResponseEntity<List<ProductCategory>> getCategoriesByFeaturedAndVisible(@PathVariable Boolean featured, 
                                                                                  @PathVariable Boolean visible) {
        List<ProductCategory> categories = categoryService.getCategoriesByFeaturedAndVisible(featured, visible);
        return ResponseEntity.ok(categories);
    }

    // Get categories with product count greater than
    @GetMapping("/product-count/greater/{count}")
    public ResponseEntity<List<ProductCategory>> getCategoriesWithProductCountGreaterThan(@PathVariable Integer count) {
        List<ProductCategory> categories = categoryService.getCategoriesWithProductCountGreaterThan(count);
        return ResponseEntity.ok(categories);
    }

    // Get categories with product count less than
    @GetMapping("/product-count/less/{count}")
    public ResponseEntity<List<ProductCategory>> getCategoriesWithProductCountLessThan(@PathVariable Integer count) {
        List<ProductCategory> categories = categoryService.getCategoriesWithProductCountLessThan(count);
        return ResponseEntity.ok(categories);
    }

    // Get categories with no products
    @GetMapping("/no-products")
    public ResponseEntity<List<ProductCategory>> getCategoriesWithNoProducts() {
        List<ProductCategory> categories = categoryService.getCategoriesWithNoProducts();
        return ResponseEntity.ok(categories);
    }

    // Get categories created after date
    @GetMapping("/created-after")
    public ResponseEntity<List<ProductCategory>> getCategoriesCreatedAfter(@RequestParam String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            List<ProductCategory> categories = categoryService.getCategoriesCreatedAfter(parsedDate);
            return ResponseEntity.ok(categories);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get categories modified after date
    @GetMapping("/modified-after")
    public ResponseEntity<List<ProductCategory>> getCategoriesModifiedAfter(@RequestParam String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            List<ProductCategory> categories = categoryService.getCategoriesModifiedAfter(parsedDate);
            return ResponseEntity.ok(categories);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get categories by tag
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<ProductCategory>> getCategoriesByTag(@PathVariable String tag) {
        List<ProductCategory> categories = categoryService.getCategoriesByTag(tag);
        return ResponseEntity.ok(categories);
    }

    // Get categories by meta title
    @GetMapping("/meta-title")
    public ResponseEntity<List<ProductCategory>> getCategoriesByMetaTitle(@RequestParam String title) {
        List<ProductCategory> categories = categoryService.getCategoriesByMetaTitle(title);
        return ResponseEntity.ok(categories);
    }

    // Get categories by color
    @GetMapping("/color/{color}")
    public ResponseEntity<List<ProductCategory>> getCategoriesByColor(@PathVariable String color) {
        List<ProductCategory> categories = categoryService.getCategoriesByColor(color);
        return ResponseEntity.ok(categories);
    }

    // Get categories ordered by display order
    @GetMapping("/ordered/display-order")
    public ResponseEntity<List<ProductCategory>> getCategoriesOrderedByDisplayOrder() {
        List<ProductCategory> categories = categoryService.getAllCategoriesOrderedByDisplayOrder();
        return ResponseEntity.ok(categories);
    }

    // Get categories ordered by name
    @GetMapping("/ordered/name")
    public ResponseEntity<List<ProductCategory>> getCategoriesOrderedByName() {
        List<ProductCategory> categories = categoryService.getAllCategoriesOrderedByName();
        return ResponseEntity.ok(categories);
    }

    // Get categories ordered by product count
    @GetMapping("/ordered/product-count")
    public ResponseEntity<List<ProductCategory>> getCategoriesOrderedByProductCount() {
        List<ProductCategory> categories = categoryService.getAllCategoriesOrderedByProductCount();
        return ResponseEntity.ok(categories);
    }

    // Get categories ordered by creation date
    @GetMapping("/ordered/creation-date")
    public ResponseEntity<List<ProductCategory>> getCategoriesOrderedByCreationDate() {
        List<ProductCategory> categories = categoryService.getAllCategoriesOrderedByCreationDate();
        return ResponseEntity.ok(categories);
    }

    // Get categories ordered by modification date
    @GetMapping("/ordered/modification-date")
    public ResponseEntity<List<ProductCategory>> getCategoriesOrderedByModificationDate() {
        List<ProductCategory> categories = categoryService.getAllCategoriesOrderedByModificationDate();
        return ResponseEntity.ok(categories);
    }

    // Get categories by multiple criteria
    @GetMapping("/criteria")
    public ResponseEntity<List<ProductCategory>> getCategoriesByCriteria(
            @RequestParam(required = false) Long parentCategoryId,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Boolean isVisible,
            @RequestParam(required = false) Boolean isFeatured) {
        List<ProductCategory> categories = categoryService.getCategoriesByCriteria(parentCategoryId, active, isVisible, isFeatured);
        return ResponseEntity.ok(categories);
    }

    // Get category hierarchy
    @GetMapping("/{id}/hierarchy")
    public ResponseEntity<List<ProductCategory>> getCategoryHierarchy(@PathVariable Long id) {
        List<ProductCategory> categories = categoryService.getCategoryHierarchy(id);
        return ResponseEntity.ok(categories);
    }

    // Count subcategories
    @GetMapping("/{id}/subcategories/count")
    public ResponseEntity<Long> countSubcategories(@PathVariable Long id) {
        Long count = categoryService.countSubcategories(id);
        return ResponseEntity.ok(count);
    }

    // Activate category
    @PutMapping("/{id}/activate")
    public ResponseEntity<ProductCategory> activateCategory(@PathVariable Long id) {
        try {
            ProductCategory category = categoryService.activateCategory(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deactivate category
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ProductCategory> deactivateCategory(@PathVariable Long id) {
        try {
            ProductCategory category = categoryService.deactivateCategory(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Make category visible
    @PutMapping("/{id}/show")
    public ResponseEntity<ProductCategory> makeCategoryVisible(@PathVariable Long id) {
        try {
            ProductCategory category = categoryService.makeCategoryVisible(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Hide category
    @PutMapping("/{id}/hide")
    public ResponseEntity<ProductCategory> hideCategory(@PathVariable Long id) {
        try {
            ProductCategory category = categoryService.hideCategory(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Feature category
    @PutMapping("/{id}/feature")
    public ResponseEntity<ProductCategory> featureCategory(@PathVariable Long id) {
        try {
            ProductCategory category = categoryService.featureCategory(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Unfeature category
    @PutMapping("/{id}/unfeature")
    public ResponseEntity<ProductCategory> unfeatureCategory(@PathVariable Long id) {
        try {
            ProductCategory category = categoryService.unfeatureCategory(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update product count
    @PutMapping("/{id}/product-count")
    public ResponseEntity<ProductCategory> updateProductCount(@PathVariable Long id, 
                                                             @RequestParam Integer productCount) {
        try {
            ProductCategory category = categoryService.updateProductCount(id, productCount);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update display order
    @PutMapping("/{id}/display-order")
    public ResponseEntity<ProductCategory> updateDisplayOrder(@PathVariable Long id, 
                                                             @RequestParam Integer displayOrder) {
        try {
            ProductCategory category = categoryService.updateDisplayOrder(id, displayOrder);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update category tags
    @PutMapping("/{id}/tags")
    public ResponseEntity<ProductCategory> updateCategoryTags(@PathVariable Long id, 
                                                             @RequestParam String tags) {
        try {
            ProductCategory category = categoryService.updateCategoryTags(id, tags);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 