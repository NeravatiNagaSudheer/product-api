package com.example.productapi.controller;

import com.example.productapi.dto.RequestDto;
import com.example.productapi.dto.ResponseDto;
import com.example.productapi.entity.Product;
import com.example.productapi.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ResponseDto>> createProducts(@RequestBody List<@Valid RequestDto> requestDtos){
        //return ResponseEntity.ok(productService.createProducts(requestDtos));
        return ResponseEntity.ok(productService.createProducts(requestDtos));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable @Min(1) Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping("/singleProduct")
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody RequestDto requestDto){
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ResponseDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable @Min(1) Long id, @Valid @RequestBody RequestDto requestDto){
       ResponseDto updateProduct  = productService.updateProduct(id,requestDto);

        //return ResponseEntity.ok("Product Updated Successfully with id:" + id);
        return  ResponseEntity.ok(updateProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductId(@PathVariable @Min(1) Long id){
        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }

}
