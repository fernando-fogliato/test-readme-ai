package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.entity.Product;
import com.example.springbootcrudapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            productService.incrementViewCount(id);
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Create new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, 
                                               @Valid @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Find product by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Optional<Product> product = productService.getProductByName(name);
        return product.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Find product by SKU
    @GetMapping("/sku/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
        Optional<Product> product = productService.getProductBySku(sku);
        return product.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Search products by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }

    // Search products by description
    @GetMapping("/search/description")
    public ResponseEntity<List<Product>> searchProductsByDescription(@RequestParam String description) {
        List<Product> products = productService.searchProductsByDescription(description);
        return ResponseEntity.ok(products);
    }

    // Search products by multiple fields
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String searchTerm) {
        List<Product> products = productService.searchProducts(searchTerm);
        return ResponseEntity.ok(products);
    }

    // Get products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    // Get products by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    // Get products by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Product>> getProductsByStatus(@PathVariable Product.ProductStatus status) {
        List<Product> products = productService.getProductsByStatus(status);
        return ResponseEntity.ok(products);
    }

    // Get active products
    @GetMapping("/active")
    public ResponseEntity<List<Product>> getActiveProducts() {
        List<Product> products = productService.getActiveProducts();
        return ResponseEntity.ok(products);
    }

    // Get featured products
    @GetMapping("/featured")
    public ResponseEntity<List<Product>> getFeaturedProducts() {
        List<Product> products = productService.getFeaturedProducts();
        return ResponseEntity.ok(products);
    }

    // Get digital products
    @GetMapping("/digital")
    public ResponseEntity<List<Product>> getDigitalProducts() {
        List<Product> products = productService.getDigitalProducts();
        return ResponseEntity.ok(products);
    }

    // Get products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam BigDecimal minPrice, 
                                                               @RequestParam BigDecimal maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    // Get products on sale
    @GetMapping("/on-sale")
    public ResponseEntity<List<Product>> getProductsOnSale() {
        List<Product> products = productService.getProductsOnSale();
        return ResponseEntity.ok(products);
    }

    // Get out of stock products
    @GetMapping("/out-of-stock")
    public ResponseEntity<List<Product>> getOutOfStockProducts() {
        List<Product> products = productService.getOutOfStockProducts();
        return ResponseEntity.ok(products);
    }

    // Get low stock products
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts() {
        List<Product> products = productService.getLowStockProducts();
        return ResponseEntity.ok(products);
    }

    // Get best selling products
    @GetMapping("/best-selling")
    public ResponseEntity<List<Product>> getBestSellingProducts(@RequestParam(defaultValue = "0") Integer salesCount) {
        List<Product> products = productService.getBestSellingProducts(salesCount);
        return ResponseEntity.ok(products);
    }

    // Get most viewed products
    @GetMapping("/most-viewed")
    public ResponseEntity<List<Product>> getMostViewedProducts(@RequestParam(defaultValue = "0") Integer viewCount) {
        List<Product> products = productService.getMostViewedProducts(viewCount);
        return ResponseEntity.ok(products);
    }

    // Get highly rated products
    @GetMapping("/highly-rated")
    public ResponseEntity<List<Product>> getHighlyRatedProducts(@RequestParam(defaultValue = "4.0") Double rating,
                                                              @RequestParam(defaultValue = "1") Integer minReviews) {
        List<Product> products = productService.getHighlyRatedProducts(rating, minReviews);
        return ResponseEntity.ok(products);
    }

    // Get products ordered by name
    @GetMapping("/ordered/name")
    public ResponseEntity<List<Product>> getProductsOrderedByName() {
        List<Product> products = productService.getAllProductsOrderedByName();
        return ResponseEntity.ok(products);
    }

    // Get products ordered by price (lowest first)
    @GetMapping("/ordered/price-asc")
    public ResponseEntity<List<Product>> getProductsOrderedByPriceAsc() {
        List<Product> products = productService.getAllProductsOrderedByPriceAsc();
        return ResponseEntity.ok(products);
    }

    // Get products ordered by price (highest first)
    @GetMapping("/ordered/price-desc")
    public ResponseEntity<List<Product>> getProductsOrderedByPriceDesc() {
        List<Product> products = productService.getAllProductsOrderedByPriceDesc();
        return ResponseEntity.ok(products);
    }

    // Get products by multiple criteria
    @GetMapping("/criteria")
    public ResponseEntity<List<Product>> getProductsByCriteria(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Product.ProductStatus status,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Boolean isFeatured,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<Product> products = productService.getProductsByCriteria(categoryId, brand, status, active, isFeatured, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    // Activate product
    @PutMapping("/{id}/activate")
    public ResponseEntity<Product> activateProduct(@PathVariable Long id) {
        try {
            Product product = productService.activateProduct(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deactivate product
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Product> deactivateProduct(@PathVariable Long id) {
        try {
            Product product = productService.deactivateProduct(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Feature product
    @PutMapping("/{id}/feature")
    public ResponseEntity<Product> featureProduct(@PathVariable Long id) {
        try {
            Product product = productService.featureProduct(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update product status
    @PutMapping("/{id}/status")
    public ResponseEntity<Product> updateProductStatus(@PathVariable Long id, 
                                                      @RequestParam Product.ProductStatus status) {
        try {
            Product product = productService.updateProductStatus(id, status);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update product price
    @PutMapping("/{id}/price")
    public ResponseEntity<Product> updateProductPrice(@PathVariable Long id, 
                                                     @RequestParam BigDecimal price) {
        try {
            Product product = productService.updateProductPrice(id, price);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update stock quantity
    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockQuantity(@PathVariable Long id, 
                                                      @RequestParam Integer stockQuantity) {
        try {
            Product product = productService.updateStockQuantity(id, stockQuantity);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update product rating
    @PutMapping("/{id}/rating")
    public ResponseEntity<Product> updateProductRating(@PathVariable Long id, 
                                                      @RequestParam Double rating,
                                                      @RequestParam Integer reviewCount) {
        try {
            Product product = productService.updateProductRating(id, rating, reviewCount);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 