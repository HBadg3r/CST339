package com.gcu.service;

import java.util.List;

import com.gcu.model.ProductModel;

public interface MenuServiceInterface {

	public void test();
	
	public List<ProductModel> getMenuItems();
	public void updateMenu(ProductModel product);
	
	public void init();
	public void destroy();
}
