package com.ProductAPI.app.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.ProductAPI.app.DTOs.ProductDTO;
import com.ProductAPI.app.EntityObjects.Product;
import com.ProductAPI.app.ServiceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") Integer id) {
		Product product = productService.getProductForId(id);
		// convert entity to DTO
		ProductDTO productResponse = modelMapper.map(product, ProductDTO.class);
		return ResponseEntity.ok().body(productResponse);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDto) {
		// convert DTO to entity
		Product productRequest = modelMapper.map(productDto, Product.class);
		Product product = productService.createProduct(productRequest);
		// convert entity to DTO
		ProductDTO productResponse = modelMapper.map(product, ProductDTO.class);
		return new ResponseEntity<ProductDTO>(productResponse, HttpStatus.CREATED);
	}

	// change the request for DTO
	// change the response for DTO
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable(name = "id") Integer id, @RequestBody ProductDTO productDto) {
		// convert DTO to Entity
		Product productRequest = modelMapper.map(productDto, Product.class);
		Product product = productService.updateProduct(id, productRequest);
		// entity to DTO
		ProductDTO productResponse = modelMapper.map(product, ProductDTO.class);
		return ResponseEntity.ok().body(productResponse);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
	}
}
