package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.entity.Product;
import com.example.springbootcrudapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Create a new product
    public Product createProduct(Product product) {
        // Check if product name already exists
        if (productRepository.existsByName(product.getName())) {
            throw new RuntimeException("Product name already exists: " + product.getName());
        }
        
        // Check if SKU already exists (if provided)
        if (product.getSku() != null && !product.getSku().isEmpty() &&
            productRepository.existsBySku(product.getSku())) {
            throw new RuntimeException("SKU already exists: " + product.getSku());
        }
        
        // Set creation and modification dates
        Date now = new Date();
        product.setCreatedDate(now);
        product.setLastModifiedDate(now);
        
        // Initialize counters if not set
        if (product.getStockQuantity() == null) {
            product.setStockQuantity(0);
        }
        if (product.getViewCount() == null) {
            product.setViewCount(0);
        }
        if (product.getSalesCount() == null) {
            product.setSalesCount(0);
        }
        if (product.getReviewCount() == null) {
            product.setReviewCount(0);
        }
        if (product.getRating() == null) {
            product.setRating(0.0);
        }
        
        return productRepository.save(product);
    }

    // Update product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Check if name is being changed and if it already exists
        if (!product.getName().equals(productDetails.getName()) && 
            productRepository.existsByName(productDetails.getName())) {
            throw new RuntimeException("Product name already exists: " + productDetails.getName());
        }
        
        // Check if SKU is being changed and if it already exists
        if (productDetails.getSku() != null && !productDetails.getSku().isEmpty() &&
            !productDetails.getSku().equals(product.getSku()) &&
            productRepository.existsBySku(productDetails.getSku())) {
            throw new RuntimeException("SKU already exists: " + productDetails.getSku());
        }

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setLongDescription(productDetails.getLongDescription());
        product.setSku(productDetails.getSku());
        product.setBrand(productDetails.getBrand());
        product.setModel(productDetails.getModel());
        product.setPrice(productDetails.getPrice());
        product.setCost(productDetails.getCost());
        product.setSalePrice(productDetails.getSalePrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setMinStockLevel(productDetails.getMinStockLevel());
        product.setMaxStockLevel(productDetails.getMaxStockLevel());
        product.setCategoryId(productDetails.getCategoryId());
        product.setWeight(productDetails.getWeight());
        product.setWeightUnit(productDetails.getWeightUnit());
        product.setDimensions(productDetails.getDimensions());
        product.setColor(productDetails.getColor());
        product.setSize(productDetails.getSize());
        product.setImageUrl(productDetails.getImageUrl());
        product.setImageGallery(productDetails.getImageGallery());
        product.setIsFeatured(productDetails.getIsFeatured());
        product.setIsDigital(productDetails.getIsDigital());
        product.setRequiresShipping(productDetails.getRequiresShipping());
        product.setIsTaxable(productDetails.getIsTaxable());
        product.setTrackInventory(productDetails.getTrackInventory());
        product.setAllowBackorder(productDetails.getAllowBackorder());
        product.setMetaTitle(productDetails.getMetaTitle());
        product.setMetaDescription(productDetails.getMetaDescription());
        product.setTags(productDetails.getTags());
        product.setStatus(productDetails.getStatus());
        product.setActive(productDetails.getActive());
        
        // Update last modified date
        product.setLastModifiedDate(new Date());

        return productRepository.save(product);
    }

    // Delete product
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    // Find product by name
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    // Find product by SKU
    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    // Search products by name
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Search products by description
    public List<Product> searchProductsByDescription(String description) {
        return productRepository.findByDescriptionContainingIgnoreCase(description);
    }

    // Find products by brand
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    // Search products by brand
    public List<Product> searchProductsByBrand(String brand) {
        return productRepository.findByBrandContainingIgnoreCase(brand);
    }

    // Find products by model
    public List<Product> getProductsByModel(String model) {
        return productRepository.findByModel(model);
    }

    // Find products by category
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // Find products by category and active status
    public List<Product> getProductsByCategoryAndActive(Long categoryId, Boolean active) {
        return productRepository.findByCategoryIdAndActive(categoryId, active);
    }

    // Find products by status
    public List<Product> getProductsByStatus(Product.ProductStatus status) {
        return productRepository.findByStatus(status);
    }

    // Find products by status and active
    public List<Product> getProductsByStatusAndActive(Product.ProductStatus status, Boolean active) {
        return productRepository.findByStatusAndActive(status, active);
    }

    // Find active products
    public List<Product> getActiveProducts() {
        return productRepository.findByActive(true);
    }

    // Find inactive products
    public List<Product> getInactiveProducts() {
        return productRepository.findByActive(false);
    }

    // Find featured products
    public List<Product> getFeaturedProducts() {
        return productRepository.findByIsFeatured(true);
    }

    // Find non-featured products
    public List<Product> getNonFeaturedProducts() {
        return productRepository.findByIsFeatured(false);
    }

    // Find digital products
    public List<Product> getDigitalProducts() {
        return productRepository.findByIsDigital(true);
    }

    // Find physical products
    public List<Product> getPhysicalProducts() {
        return productRepository.findByIsDigital(false);
    }

    // Find products that require shipping
    public List<Product> getProductsRequiringShipping() {
        return productRepository.findByRequiresShipping(true);
    }

    // Find products that don't require shipping
    public List<Product> getProductsNotRequiringShipping() {
        return productRepository.findByRequiresShipping(false);
    }

    // Find taxable products
    public List<Product> getTaxableProducts() {
        return productRepository.findByIsTaxable(true);
    }

    // Find non-taxable products
    public List<Product> getNonTaxableProducts() {
        return productRepository.findByIsTaxable(false);
    }

    // Find products with inventory tracking
    public List<Product> getProductsWithInventoryTracking() {
        return productRepository.findByTrackInventory(true);
    }

    // Find products without inventory tracking
    public List<Product> getProductsWithoutInventoryTracking() {
        return productRepository.findByTrackInventory(false);
    }

    // Find products by color
    public List<Product> getProductsByColor(String color) {
        return productRepository.findByColor(color);
    }

    // Find products by size
    public List<Product> getProductsBySize(String size) {
        return productRepository.findBySize(size);
    }

    // Find products by price range
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    // Find products by price greater than
    public List<Product> getProductsByPriceGreaterThan(BigDecimal price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    // Find products by price less than
    public List<Product> getProductsByPriceLessThan(BigDecimal price) {
        return productRepository.findByPriceLessThan(price);
    }

    // Find products on sale
    public List<Product> getProductsOnSale() {
        return productRepository.findProductsOnSale();
    }

    // Find products by stock quantity greater than
    public List<Product> getProductsByStockQuantityGreaterThan(Integer quantity) {
        return productRepository.findByStockQuantityGreaterThan(quantity);
    }

    // Find products by stock quantity less than
    public List<Product> getProductsByStockQuantityLessThan(Integer quantity) {
        return productRepository.findByStockQuantityLessThan(quantity);
    }

    // Find out of stock products
    public List<Product> getOutOfStockProducts() {
        return productRepository.findOutOfStockProducts();
    }

    // Find low stock products
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts();
    }

    // Find products by rating greater than
    public List<Product> getProductsByRatingGreaterThan(Double rating) {
        return productRepository.findByRatingGreaterThan(rating);
    }

    // Find products by rating range
    public List<Product> getProductsByRatingRange(Double minRating, Double maxRating) {
        return productRepository.findByRatingRange(minRating, maxRating);
    }

    // Find products created after date
    public List<Product> getProductsCreatedAfter(Date date) {
        return productRepository.findProductsCreatedAfter(date);
    }

    // Find products published after date
    public List<Product> getProductsPublishedAfter(Date date) {
        return productRepository.findProductsPublishedAfter(date);
    }

    // Find products modified after date
    public List<Product> getProductsModifiedAfter(Date date) {
        return productRepository.findProductsModifiedAfter(date);
    }

    // Find products by tag
    public List<Product> getProductsByTag(String tag) {
        return productRepository.findByTagsContaining(tag);
    }

    // Find products by meta title
    public List<Product> getProductsByMetaTitle(String title) {
        return productRepository.findByMetaTitleContaining(title);
    }

    // Find best selling products
    public List<Product> getBestSellingProducts(Integer salesCount) {
        return productRepository.findBestSellingProducts(salesCount);
    }

    // Find most viewed products
    public List<Product> getMostViewedProducts(Integer viewCount) {
        return productRepository.findMostViewedProducts(viewCount);
    }

    // Find highly rated products
    public List<Product> getHighlyRatedProducts(Double rating, Integer minReviews) {
        return productRepository.findHighlyRatedProducts(rating, minReviews);
    }

    // Get all products ordered by name
    public List<Product> getAllProductsOrderedByName() {
        return productRepository.findAllByOrderByNameAsc();
    }

    // Get all products ordered by price (lowest first)
    public List<Product> getAllProductsOrderedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    // Get all products ordered by price (highest first)
    public List<Product> getAllProductsOrderedByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    // Get all products ordered by creation date (newest first)
    public List<Product> getAllProductsOrderedByCreationDate() {
        return productRepository.findAllByOrderByCreatedDateDesc();
    }

    // Get all products ordered by rating (highest first)
    public List<Product> getAllProductsOrderedByRating() {
        return productRepository.findAllByOrderByRatingDesc();
    }

    // Get all products ordered by sales count (highest first)
    public List<Product> getAllProductsOrderedBySalesCount() {
        return productRepository.findAllByOrderBySalesCountDesc();
    }

    // Get all products ordered by view count (highest first)
    public List<Product> getAllProductsOrderedByViewCount() {
        return productRepository.findAllByOrderByViewCountDesc();
    }

    // Get all products ordered by stock quantity (highest first)
    public List<Product> getAllProductsOrderedByStockQuantity() {
        return productRepository.findAllByOrderByStockQuantityDesc();
    }

    // Find products by multiple criteria
    public List<Product> getProductsByCriteria(Long categoryId, String brand, Product.ProductStatus status,
                                             Boolean active, Boolean isFeatured, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findProductsByCriteria(categoryId, brand, status, active, isFeatured, minPrice, maxPrice);
    }

    // Search products by multiple fields
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.searchProducts(searchTerm);
    }

    // Count products by category
    public Long countProductsByCategory(Long categoryId) {
        return productRepository.countProductsByCategory(categoryId);
    }

    // Count products by brand
    public Long countProductsByBrand(String brand) {
        return productRepository.countProductsByBrand(brand);
    }

    // Count products by status
    public Long countProductsByStatus(Product.ProductStatus status) {
        return productRepository.countProductsByStatus(status);
    }

    // Activate product
    public Product activateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(true);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Deactivate product
    public Product deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(false);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Feature product
    public Product featureProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setIsFeatured(true);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Unfeature product
    public Product unfeatureProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setIsFeatured(false);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Update product status
    public Product updateProductStatus(Long id, Product.ProductStatus status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setStatus(status);
        product.setLastModifiedDate(new Date());
        
        // Set published date when status changes to PUBLISHED
        if (status == Product.ProductStatus.PUBLISHED && product.getPublishedDate() == null) {
            product.setPublishedDate(new Date());
        }
        
        return productRepository.save(product);
    }

    // Update product price
    public Product updateProductPrice(Long id, BigDecimal price) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setPrice(price);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Update product sale price
    public Product updateProductSalePrice(Long id, BigDecimal salePrice) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setSalePrice(salePrice);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Update stock quantity
    public Product updateStockQuantity(Long id, Integer stockQuantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setStockQuantity(stockQuantity);
        product.setLastModifiedDate(new Date());
        
        // Update status to OUT_OF_STOCK if quantity is 0
        if (stockQuantity == 0) {
            product.setStatus(Product.ProductStatus.OUT_OF_STOCK);
        } else if (product.getStatus() == Product.ProductStatus.OUT_OF_STOCK) {
            product.setStatus(Product.ProductStatus.PUBLISHED);
        }
        
        return productRepository.save(product);
    }

    // Increment view count
    public Product incrementViewCount(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setViewCount(product.getViewCount() + 1);
        return productRepository.save(product);
    }

    // Increment sales count
    public Product incrementSalesCount(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setSalesCount(product.getSalesCount() + 1);
        return productRepository.save(product);
    }

    // Update product rating
    public Product updateProductRating(Long id, Double rating, Integer reviewCount) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setRating(rating);
        product.setReviewCount(reviewCount);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    // Update product tags
    public Product updateProductTags(Long id, String tags) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setTags(tags);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }
} 