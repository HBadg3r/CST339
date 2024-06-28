package com.gcu.service;

import java.util.List;

import com.gcu.model.ProductModel;

public interface ProductServiceInterface {

	public void test();
	
	public List<ProductModel> getProductItems();
	
	public void updateProducts(ProductModel product);
	
	public void init();
	
	public void destroy();
}
