package com.cjc.main.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.main.crud.model.Category;
import com.cjc.main.crud.model.Product;
import com.cjc.main.crud.service.CategoryService;
import com.cjc.main.crud.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService ps;
	
	@Autowired
	CategoryService cs;
	
	//saving new Product
	
	@PostMapping("/api/product/{categoryId}")
	public String saveProduct(@RequestBody Product p,@PathVariable()int categoryId)
	{
		ps.saveProduct(p, categoryId);
		return "savedata";
	}
	
	
	
	//get all product
	
	@GetMapping("/api/products")
	public List<Product> getAllProduct()
	{
		List<Product> lp=ps.getAllProduce();
		
		return lp;
	}
	
	
	
	//get product by pid
	
	@GetMapping("/api/product/{pid}")
	public Product getProductByPid(@PathVariable("pid")int pid)
	{
		
		Product p=ps.findByPid(pid);
		System.out.println(p);
		return p;
	}
	
	
	//ipdate product by pid
	
	@PutMapping("/api/update/{pid}")
	public String updateProduct(@PathVariable("pid")int pid ,@RequestBody Product p)
	{
		Product p1=ps.findByPid(pid);
		p1.setPname(p.getPname());
		p1.setPprice(p.getPprice());
	
		ps.saveProduct(p1, p1.getCategory().getCategoryId());
		System.out.println(p1);
		
		return "update";
	}
	//delete by pid 
	
	@DeleteMapping("/api/deletebypid/{pid}")
	public String deleteProductByPid(@PathVariable("pid")int pid)
	{
		Product p=ps.findByPid(pid);
		ps.deleteByPid(p);
		return "deleted";
	}
	
	//product on page
	
	 @GetMapping("/api/getproductByPage/{page}")
	    public List<Product> getAllProducts(
	            @PathVariable int page
	            ) 
	 {
		   
	        Pageable paging = PageRequest.of(
	            page,2);
	        Page<Product> pages = ps.getAllProduct(paging);
	 
	      
	        return pages.getContent();
	        
	    }

	
	 
	
	
	
}
