package com.ProductAPI.app.Service;

import java.util.List;

import com.ProductAPI.app.EntityObjects.Product;

public interface ProductService {
	public Product createProduct(Product product);
	public List<Product> getAllProducts();
	public Product getProductForId(Integer id);
	public Product updateProduct(Integer id, Product Product);
	public void deleteProduct(Integer id);
}
