package com.ProductAPI.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProductAPI.app.EntityObjects.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
