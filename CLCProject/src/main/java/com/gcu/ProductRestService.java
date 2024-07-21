package com.gcu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsRestService 
{
	@Autowired
	private ProductService service;
	
	@Autowired
	public ProductsRestService(ProductService service) 
	{
        this.service = service;
    	}
	
	public ResponseEntity<?> getProducts()
	{
		try
		{
			List<ProductModel> products = service.getAllProducts();
			if(products.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(users, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
