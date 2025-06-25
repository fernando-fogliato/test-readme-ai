package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find product by name
    Optional<Product> findByName(String name);

    // Find product by SKU
    Optional<Product> findBySku(String sku);

    // Find products by name containing (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find products by description containing (case-insensitive)
    List<Product> findByDescriptionContainingIgnoreCase(String description);

    // Find products by brand
    List<Product> findByBrand(String brand);

    // Find products by brand containing (case-insensitive)
    List<Product> findByBrandContainingIgnoreCase(String brand);

    // Find products by model
    List<Product> findByModel(String model);

    // Find products by category ID
    List<Product> findByCategoryId(Long categoryId);

    // Find products by category ID and active status
    List<Product> findByCategoryIdAndActive(Long categoryId, Boolean active);

    // Find products by status
    List<Product> findByStatus(Product.ProductStatus status);

    // Find products by status and active
    List<Product> findByStatusAndActive(Product.ProductStatus status, Boolean active);

    // Find active products
    List<Product> findByActive(Boolean active);

    // Find featured products
    List<Product> findByIsFeatured(Boolean isFeatured);

    // Find digital products
    List<Product> findByIsDigital(Boolean isDigital);

    // Find products that require shipping
    List<Product> findByRequiresShipping(Boolean requiresShipping);

    // Find taxable products
    List<Product> findByIsTaxable(Boolean isTaxable);

    // Find products with inventory tracking
    List<Product> findByTrackInventory(Boolean trackInventory);

    // Find products by color
    List<Product> findByColor(String color);

    // Find products by size
    List<Product> findBySize(String size);

    // Custom query to find products by price range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    // Custom query to find products by price greater than
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findByPriceGreaterThan(@Param("price") BigDecimal price);

    // Custom query to find products by price less than
    @Query("SELECT p FROM Product p WHERE p.price < :price")
    List<Product> findByPriceLessThan(@Param("price") BigDecimal price);

    // Custom query to find products on sale (sale price is not null and less than regular price)
    @Query("SELECT p FROM Product p WHERE p.salePrice IS NOT NULL AND p.salePrice < p.price")
    List<Product> findProductsOnSale();

    // Custom query to find products by stock quantity greater than
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > :quantity")
    List<Product> findByStockQuantityGreaterThan(@Param("quantity") Integer quantity);

    // Custom query to find products by stock quantity less than
    @Query("SELECT p FROM Product p WHERE p.stockQuantity < :quantity")
    List<Product> findByStockQuantityLessThan(@Param("quantity") Integer quantity);

    // Custom query to find out of stock products
    @Query("SELECT p FROM Product p WHERE p.stockQuantity = 0 OR p.stockQuantity IS NULL")
    List<Product> findOutOfStockProducts();

    // Custom query to find low stock products (below minimum stock level)
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= p.minStockLevel AND p.minStockLevel > 0")
    List<Product> findLowStockProducts();

    // Custom query to find products by rating greater than
    @Query("SELECT p FROM Product p WHERE p.rating > :rating")
    List<Product> findByRatingGreaterThan(@Param("rating") Double rating);

    // Custom query to find products by rating range
    @Query("SELECT p FROM Product p WHERE p.rating BETWEEN :minRating AND :maxRating")
    List<Product> findByRatingRange(@Param("minRating") Double minRating, @Param("maxRating") Double maxRating);

    // Custom query to find products created after date
    @Query("SELECT p FROM Product p WHERE p.createdDate > :date")
    List<Product> findProductsCreatedAfter(@Param("date") Date date);

    // Custom query to find products published after date
    @Query("SELECT p FROM Product p WHERE p.publishedDate > :date")
    List<Product> findProductsPublishedAfter(@Param("date") Date date);

    // Custom query to find products modified after date
    @Query("SELECT p FROM Product p WHERE p.lastModifiedDate > :date")
    List<Product> findProductsModifiedAfter(@Param("date") Date date);

    // Custom query to find products by tags containing
    @Query("SELECT p FROM Product p WHERE p.tags LIKE %:tag%")
    List<Product> findByTagsContaining(@Param("tag") String tag);

    // Custom query to find products by meta title containing
    @Query("SELECT p FROM Product p WHERE p.metaTitle LIKE %:title%")
    List<Product> findByMetaTitleContaining(@Param("title") String title);

    // Custom query to find best selling products (highest sales count)
    @Query("SELECT p FROM Product p WHERE p.salesCount > :salesCount ORDER BY p.salesCount DESC")
    List<Product> findBestSellingProducts(@Param("salesCount") Integer salesCount);

    // Custom query to find most viewed products
    @Query("SELECT p FROM Product p WHERE p.viewCount > :viewCount ORDER BY p.viewCount DESC")
    List<Product> findMostViewedProducts(@Param("viewCount") Integer viewCount);

    // Custom query to find highly rated products
    @Query("SELECT p FROM Product p WHERE p.rating >= :rating AND p.reviewCount >= :minReviews ORDER BY p.rating DESC")
    List<Product> findHighlyRatedProducts(@Param("rating") Double rating, @Param("minReviews") Integer minReviews);

    // Check if product exists by name
    boolean existsByName(String name);

    // Check if product exists by SKU
    boolean existsBySku(String sku);

    // Find products ordered by name
    List<Product> findAllByOrderByNameAsc();

    // Find products ordered by price (lowest first)
    List<Product> findAllByOrderByPriceAsc();

    // Find products ordered by price (highest first)
    List<Product> findAllByOrderByPriceDesc();

    // Find products ordered by creation date (newest first)
    List<Product> findAllByOrderByCreatedDateDesc();

    // Find products ordered by rating (highest first)
    List<Product> findAllByOrderByRatingDesc();

    // Find products ordered by sales count (highest first)
    List<Product> findAllByOrderBySalesCountDesc();

    // Find products ordered by view count (highest first)
    List<Product> findAllByOrderByViewCountDesc();

    // Find products ordered by stock quantity (highest first)
    List<Product> findAllByOrderByStockQuantityDesc();

    // Custom query to find products by multiple criteria
    @Query("SELECT p FROM Product p WHERE " +
           "(:categoryId IS NULL OR p.categoryId = :categoryId) AND " +
           "(:brand IS NULL OR p.brand = :brand) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:active IS NULL OR p.active = :active) AND " +
           "(:isFeatured IS NULL OR p.isFeatured = :isFeatured) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findProductsByCriteria(@Param("categoryId") Long categoryId,
                                       @Param("brand") String brand,
                                       @Param("status") Product.ProductStatus status,
                                       @Param("active") Boolean active,
                                       @Param("isFeatured") Boolean isFeatured,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice);

    // Custom query to search products by multiple fields
    @Query("SELECT p FROM Product p WHERE " +
           "p.name LIKE %:searchTerm% OR " +
           "p.description LIKE %:searchTerm% OR " +
           "p.brand LIKE %:searchTerm% OR " +
           "p.model LIKE %:searchTerm% OR " +
           "p.tags LIKE %:searchTerm%")
    List<Product> searchProducts(@Param("searchTerm") String searchTerm);

    // Count products by category
    @Query("SELECT COUNT(p) FROM Product p WHERE p.categoryId = :categoryId")
    Long countProductsByCategory(@Param("categoryId") Long categoryId);

    // Count products by brand
    @Query("SELECT COUNT(p) FROM Product p WHERE p.brand = :brand")
    Long countProductsByBrand(@Param("brand") String brand);

    // Count products by status
    @Query("SELECT COUNT(p) FROM Product p WHERE p.status = :status")
    Long countProductsByStatus(@Param("status") Product.ProductStatus status);
} 