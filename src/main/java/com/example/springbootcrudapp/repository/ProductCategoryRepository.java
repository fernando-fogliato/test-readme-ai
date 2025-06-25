package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    // Find category by name
    Optional<ProductCategory> findByName(String name);

    // Find category by category code
    Optional<ProductCategory> findByCategoryCode(String categoryCode);

    // Find categories by name containing (case-insensitive)
    List<ProductCategory> findByNameContainingIgnoreCase(String name);

    // Find categories by description containing (case-insensitive)
    List<ProductCategory> findByDescriptionContainingIgnoreCase(String description);

    // Find categories by parent category ID
    List<ProductCategory> findByParentCategoryId(Long parentCategoryId);

    // Find root categories (no parent)
    List<ProductCategory> findByParentCategoryIdIsNull();

    // Find categories by parent category ID and active status
    List<ProductCategory> findByParentCategoryIdAndActive(Long parentCategoryId, Boolean active);

    // Find root categories that are active
    List<ProductCategory> findByParentCategoryIdIsNullAndActive(Boolean active);

    // Find active categories
    List<ProductCategory> findByActive(Boolean active);

    // Find visible categories
    List<ProductCategory> findByIsVisible(Boolean isVisible);

    // Find featured categories
    List<ProductCategory> findByIsFeatured(Boolean isFeatured);

    // Find categories by active status and visibility
    List<ProductCategory> findByActiveAndIsVisible(Boolean active, Boolean isVisible);

    // Find categories by featured status and visibility
    List<ProductCategory> findByIsFeaturedAndIsVisible(Boolean isFeatured, Boolean isVisible);

    // Custom query to find categories by product count greater than
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.productCount > :productCount")
    List<ProductCategory> findByProductCountGreaterThan(@Param("productCount") Integer productCount);

    // Custom query to find categories by product count less than
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.productCount < :productCount")
    List<ProductCategory> findByProductCountLessThan(@Param("productCount") Integer productCount);

    // Custom query to find categories with no products
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.productCount = 0 OR pc.productCount IS NULL")
    List<ProductCategory> findCategoriesWithNoProducts();

    // Custom query to find categories created after date
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.createdDate > :date")
    List<ProductCategory> findCategoriesCreatedAfter(@Param("date") Date date);

    // Custom query to find categories modified after date
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.lastModifiedDate > :date")
    List<ProductCategory> findCategoriesModifiedAfter(@Param("date") Date date);

    // Custom query to find categories by tags containing
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.tags LIKE %:tag%")
    List<ProductCategory> findByTagsContaining(@Param("tag") String tag);

    // Custom query to find categories by meta title containing
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.metaTitle LIKE %:title%")
    List<ProductCategory> findByMetaTitleContaining(@Param("title") String title);

    // Custom query to find categories by color
    List<ProductCategory> findByColor(String color);

    // Check if category exists by name
    boolean existsByName(String name);

    // Check if category exists by category code
    boolean existsByCategoryCode(String categoryCode);

    // Find categories ordered by display order
    List<ProductCategory> findAllByOrderByDisplayOrderAsc();

    // Find categories ordered by name
    List<ProductCategory> findAllByOrderByNameAsc();

    // Find categories ordered by product count (highest first)
    List<ProductCategory> findAllByOrderByProductCountDesc();

    // Find categories ordered by creation date (newest first)
    List<ProductCategory> findAllByOrderByCreatedDateDesc();

    // Find categories ordered by last modified date (most recent first)
    List<ProductCategory> findAllByOrderByLastModifiedDateDesc();

    // Custom query to find categories by multiple criteria
    @Query("SELECT pc FROM ProductCategory pc WHERE " +
           "(:parentCategoryId IS NULL OR pc.parentCategoryId = :parentCategoryId) AND " +
           "(:active IS NULL OR pc.active = :active) AND " +
           "(:isVisible IS NULL OR pc.isVisible = :isVisible) AND " +
           "(:isFeatured IS NULL OR pc.isFeatured = :isFeatured)")
    List<ProductCategory> findCategoriesByCriteria(@Param("parentCategoryId") Long parentCategoryId,
                                                 @Param("active") Boolean active,
                                                 @Param("isVisible") Boolean isVisible,
                                                 @Param("isFeatured") Boolean isFeatured);

    // Custom query to get category hierarchy (parent and children)
    @Query("SELECT pc FROM ProductCategory pc WHERE pc.id = :categoryId OR pc.parentCategoryId = :categoryId")
    List<ProductCategory> findCategoryHierarchy(@Param("categoryId") Long categoryId);

    // Custom query to count subcategories
    @Query("SELECT COUNT(pc) FROM ProductCategory pc WHERE pc.parentCategoryId = :parentCategoryId")
    Long countSubcategories(@Param("parentCategoryId") Long parentCategoryId);
} 