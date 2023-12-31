package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
@Service
public class CategoryService {

@Autowired
CategoryRepo categoryRepo;
@Autowired
ModelMapper modelMapper;
@Autowired
ProductRepo productRepo;

public void createcategory(CategoryDTO categorydto){
	Category category = modelMapper.map(categorydto,Category.class);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  LocalDateTime now = LocalDateTime.now();  
	 category.setCreated_at(dtf.format(now));
	 category.setUpdated_at(dtf.format(now));
	 categoryRepo.save(category);
     }
public String updatecategory(CategoryDTO categorydto) {
	Optional <Category> categoryoptional = categoryRepo.findById(categorydto.getCategoryid());
	if(categoryoptional.isPresent()) {
		Category category=categoryoptional.get();
		category.setTitle(categorydto.getTitle());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();  
		 category.setUpdated_at(dtf.format(now));
		 categoryRepo.save(category);
		 return "update";
	  }return "not update";
   } 

public  List<CategoryDTO> getAllCategories(){
	List<Category> categoryList=categoryRepo.findAll();	
	List<CategoryDTO> categorydtoList= modelMapper.map(categoryList,new TypeToken<List<CategoryDTO>>() {}.getType() );
     return categorydtoList;
     } 
public CategoryDTO getCategoriesById(Long categoryid) {
	 Optional<Category> category = categoryRepo.findById(categoryid);
	     if(category.isPresent()) {
	    	 CategoryDTO categorydto= modelMapper.map(category,CategoryDTO.class);	 
		  return categorydto;
	     }
	 return null;
      }
public int deleteItemById( Long categoryid) {
	Optional<Category> category=categoryRepo.findById(categoryid);
	if(category.isPresent()) {
		categoryRepo.deleteById(categoryid);
	  return 0;
	}else
	  return 1;
	  }





}
