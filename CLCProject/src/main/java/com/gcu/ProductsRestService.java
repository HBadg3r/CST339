package com.gcu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;

@RestController
public class ProductsRestService 
{
	@Autowired
	private ProductService service;
	
	public ProductsRestService(ProductService service) 
	{
        this.service = service;
    	}
	
	public ResponseEntity<?> getProducts()
	{
		try
		{
			List<ProductModel> products = service.getProductItems();
			if(products.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(products, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
