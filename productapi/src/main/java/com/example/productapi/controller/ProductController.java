package com.example.productapi.controller;

import com.example.productapi.dto.RequestDto;
import com.example.productapi.dto.ResponseDto;
import com.example.productapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Product management endpoints")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Create multiple products",
            description = "Creates a list of products in bulk. Requires ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Access denied — ADMIN role required")
    })
    @PostMapping("/bulk")
    public ResponseEntity<List<ResponseDto>> createProducts(@RequestBody List<@Valid RequestDto> requestDtos) {
        return ResponseEntity.ok(productService.createProducts(requestDtos));
    }

    @Operation(
            summary = "Get product by ID",
            description = "Fetches a single product by its ID. Requires USER or ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found and returned"),
            @ApiResponse(responseCode = "400", description = "ID must be 1 or greater"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(
            @Parameter(description = "ID of the product to retrieve", example = "1")
            @PathVariable @Min(1) Long id
    ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(
            summary = "Create a single product",
            description = "Creates one product. Requires ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Access denied — ADMIN role required")
    })
    @PostMapping("/singleProduct")
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @Operation(
            summary = "Get all products",
            description = "Returns a list of all products in the database. Requires USER or ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of products returned successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/allProducts")
    public ResponseEntity<List<ResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(
            summary = "Update a product",
            description = "Updates an existing product by ID. Requires ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or ID"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Access denied — ADMIN role required")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(
            @Parameter(description = "ID of the product to update", example = "1")
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody RequestDto requestDto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, requestDto));
    }

    @Operation(
            summary = "Delete a product",
            description = "Deletes a product by ID. Requires ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted successfully — no content returned"),
            @ApiResponse(responseCode = "400", description = "ID must be 1 or greater"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Access denied — ADMIN role required")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductId(
            @Parameter(description = "ID of the product to delete", example = "1")
            @PathVariable @Min(1) Long id
    ) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}