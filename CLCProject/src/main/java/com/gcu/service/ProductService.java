package com.gcu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.ProductModel;

public class ProductService implements ProductServiceInterface {

	@Autowired
	private DataAccessInterface<ProductModel> service;
	
	@Override
	public void test() {
		System.out.println("Hello from the ProductService");
		
	}

	@Override
	public void updateProducts(ProductModel product) {
		service.create(product);
	}
	
	@Override
	public void update(ProductModel product) {
		service.update(product);
	}

	@Override
	public void delete(ProductModel product) {
		service.delete(product);
	}
	
	@Override
	public List<ProductModel> getProductItems() {
		List<ProductModel> products = new ArrayList<ProductModel>();
		products = service.findAll();
		return products;
	}

	@Override
	public void init() {
		System.out.println("ProductService init");
		
	}

	@Override
	public void destroy() {
		System.out.println("ProductService destroy");
		
	}

	public ProductModel findById(int id) {
		ProductModel product = service.findById(id);
		return product;
	}
}
