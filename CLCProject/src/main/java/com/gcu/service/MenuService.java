package com.gcu.service;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.ProductModel;

public class MenuService implements MenuServiceInterface {
	public List<ProductModel> menuItems = new ArrayList<ProductModel>();
	
	public MenuService() {
	    menuItems.add(new ProductModel(1, "Beachside Margarita", "A refreshing blend of tequila, lime, and triple sec", 12.50D, "Drink"));
	    menuItems.add(new ProductModel(2, "Tropical Mojito", "Mint, lime, and rum served over ice with a splash of soda", 11.00D, "Drink"));
	    menuItems.add(new ProductModel(3, "Island Fries", "Crispy golden fries served with a side of spicy aioli", 7.00D, "Food"));
	    menuItems.add(new ProductModel(4, "Sunset Sliders", "Mini beef sliders with cheddar cheese and caramelized onions", 10.50D, "Food"));
	    menuItems.add(new ProductModel(5, "Seaside Shrimp Basket", "Fried shrimp served with tartar sauce and lemon wedges", 14.00D, "Food"));
	}
	@Override
	public void test() {
		System.out.println("Hello from the MenuService");
		
	}

	@Override
	public void updateMenu(ProductModel product) {
		menuItems.add(product);
	}
	
	
	@Override
	public List<ProductModel> getMenuItems() {
		return menuItems;
	}

	@Override
	public void init() {
		System.out.println("MenuService init");
		
	}

	@Override
	public void destroy() {
		System.out.println("MenuService destroy");
		
	}
}
