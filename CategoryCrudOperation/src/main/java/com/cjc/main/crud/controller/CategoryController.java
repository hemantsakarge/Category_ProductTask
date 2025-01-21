package com.cjc.main.crud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.main.crud.model.Category;
import com.cjc.main.crud.model.Product;
import com.cjc.main.crud.service.CategoryService;
import com.cjc.main.crud.service.ProductService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService cs;
	@Autowired
	ProductService ps;
	
	
	//saving new Categoies
	
		@PostMapping("/api/categories")
	public String saveCategory(@RequestBody Category c)
	{
		
		cs.saveCategory(c);
		return "saveCategory";
	}



	//get_all_categories
	
	@GetMapping("api/category")
	public List<Category> getAllCategory()
	{
		List<Category>list=cs.getAllCategory();
		
		return list;
	}
	

	//get singe category bu category id with product
	
	@GetMapping("/api/categories/{categoryId}/{pid}")
	public Map<String, Object> getSingleCategorySingleProduct(@PathVariable("categoryId") int categoryId, 
	                                                          @PathVariable("pid") int pid) {

	    Category category = cs.findByctegoryId(categoryId);

	    Category category1=new Category();
	    category1.setCategoryId(category.getCategoryId());
	    category1.setCategoryInfo(category.getCategoryInfo());
	    category1.setCategoryType(category.getCategoryType());

	    Map<String, Object> response = new HashMap<>();
	   
	    for(Product pr : category.getProduct())
	    {
	    	if(pr.getPid()==pid)
	    	{
	    		category1.setProduct(Arrays.asList(pr));
	    		    response.put("getCategoryId", category1);

	    	}
	    }

		  
	    return response;
	}

	
	
	//get single category by category id
	
	@GetMapping("/api/categories/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId")int categoryId)
	{
		Category c=cs.findByctegoryId(categoryId);
		
		return c;
	}
	
	//update category by id
	
	@PutMapping("/updatecategoryId/{categoryId}")
	public String updateCategoryByCategoryId(@PathVariable("categoryId")int categoryId,@RequestBody Category c)
	{
		Category c1=cs.findByctegoryId(categoryId);
		
		
		
		cs.saveCategory(c1);
		
		return "updated";
	}
	
	
	
	//delete category by id 
	
	@DeleteMapping("deleteCategorybyctegoryid/{categoryId}")
	public String deleteCategoryByCtegoryId(@PathVariable("categoryId")int categoryId)
	{
		Category c=cs.findByctegoryId(categoryId);
		cs.deleteCategory(c);
		return "deleted";
	}

	//get categroy by pagination
	
	 @GetMapping("api/categories/page/{page}")
	    public List<Category> getAllCategory(
	            @PathVariable int page
	           
	           
	            ) 
	 {
		   
    Pageable paging = PageRequest.of(
	            page, 1);
	        Page<Category> pages = cs.getAllCategoryByPage(paging);
	 
	        // Retrieve the items
	        return pages.getContent();
	        
	    }
	
	 @GetMapping("/getCategoryByPage/{page}/{size}/{sortBy}")
	    public List<Category> getAllProducts(
	            @PathVariable int page,
	            @PathVariable int size,
	            @PathVariable String sortBy
	            ) 
	 {
		  
	        Pageable paging = PageRequest.of(
	            page, size, Sort.by(sortBy).ascending());
	        Page<Category> pages = cs.getAllCategoryByPage(paging);
	 
	      
	        return pages.getContent();
	        
	    }
	
	}
	
	

