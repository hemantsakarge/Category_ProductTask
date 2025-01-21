package com.cjc.main.crud.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cjc.main.crud.model.Category;
import com.cjc.main.crud.model.Product;
import com.cjc.main.crud.repository.CategoryRepository;
import com.cjc.main.crud.repository.ProductRepository;
import com.cjc.main.crud.service.ProductService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProductSeviceImpl implements ProductService{

	@Autowired
	ProductRepository pr;
	@Autowired
	CategoryRepository cr;
	
	@Override
	public List<Product> getAllProduce() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

	@Override
	public Product findByPid(int pid) {
		// TODO Auto-generated method stub
		return pr.findByPid(pid);
	}

	
	@Override
	public Page<Product> getAllProduct(Pageable pages) {
		// TODO Auto-generated method stub
		return pr.findAll(pages);
	}

	@Override
	public void deleteByPid(Product p) {
		pr.delete(p);
	}

	@Override
	public Product saveProduct(Product p, int categoryId) {
		Category c=cr.findBycategoryId(categoryId);
		p.setCategory(c);
		return pr.save(p);
	}

	@Override
	public Page<Product> getAllProducts(Pageable pa) {
		// TODO Auto-generated method stub
		return pr.findAll(pa);
	}

	
	
	

	

	
	
}
