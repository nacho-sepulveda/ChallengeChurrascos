package com.ProductAPI.app.ServiceImpl;




import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProductAPI.app.EntityObjects.Product;
import com.ProductAPI.app.Errors.ResourceNotFoundException;
import com.ProductAPI.app.Repositories.ProductRepository;
import com.ProductAPI.app.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	 
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	@Override
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	@Override
	public Product getProductForId(Integer id) {
		Optional<Product> result = productRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResourceNotFoundException("Product", "id", id);
		}
	}
	@Override
	public Product updateProduct(Integer id, Product productRequest) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		product.setName(productRequest.getName());
		product.setSKU(productRequest.getSKU());
		product.setCode(productRequest.getCode());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setCurrency(productRequest.getCurrency());
		return productRepo.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		productRepo.delete(product);	
	}
}
