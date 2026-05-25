package com.example.productapi.service;

import com.example.productapi.dto.RequestDto;
import com.example.productapi.dto.ResponseDto;
import com.example.productapi.entity.Product;
import com.example.productapi.exception.ResourceNotFoundException;
import com.example.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Save All products
    @Transactional
    public List<ResponseDto> createProducts(List<RequestDto> requestDtos) {
        if (requestDtos == null || requestDtos.isEmpty()){
            throw new IllegalArgumentException("Product list cannot be empty");
        }
        List<Product> products = requestDtos.stream()
                .map(this::mapToEntity)
                .toList();
        List<Product> savedProducts=productRepository.saveAll(products);
        return savedProducts.stream()
                .map(this::mapToResponse)
                .toList();
    }

    //Get Product by Id
    public ResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductId is not Found!"));
        return mapToResponse(product);
    }

    //Save Single Product
    public ResponseDto createProduct(RequestDto requestDto) {

        Product saved = productRepository.save(mapToEntity(requestDto));
        return mapToResponse(saved);
    }

    //Get All Products
    public List<ResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    //Delete Product By id
    @Transactional
    public void deleteProductById(Long id) {
        //Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product" + id + "id is not Found"));
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product is not found");
        }
        productRepository.deleteById(id);
    }

    //update Product by id
    @Transactional
    public ResponseDto updateProduct(Long id,RequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product" + id + "id is not Found"));

        //Product product1 = new Product();
        product.setProductName(requestDto.getProductName());
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());

        Product updated = productRepository.save(product);
        return mapToResponse(updated);

    }
    private Product mapToEntity(RequestDto requestDto) {
        Product product = new Product();
        product.setProductName(requestDto.getProductName());
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());
        return product;
    }

    private ResponseDto mapToResponse(Product product) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(product.getId());
        responseDto.setProductName(product.getProductName());
        responseDto.setPrice(product.getPrice());
        responseDto.setQuantity(product.getQuantity());
        return responseDto;
    }
}
