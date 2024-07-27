package com.gcu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;

@RestController
public class ProductRestService {
	@Autowired
	private ProductService service;

	public ProductRestService(ProductService service) {
	}

	public ResponseEntity<?> getProductById(int id) {
		try {
			ProductModel product = service.findById(id);
			if (product == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
