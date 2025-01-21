package com.cjc.main.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.cjc.main.crud.model.Category;
import com.cjc.main.crud.model.Product;

public interface ProductService {

	List<Product> getAllProduce();
	
	public  Product findByPid(int pid);

	 public Product saveProduct(Product p, int categoryId) ;
	     
	
	Page<Product> getAllProduct(Pageable pages);

	Page<Product> getAllProducts(Pageable pa);
	
	public void deleteByPid(Product p);
}
