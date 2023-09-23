package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;

import lombok.Data;

@Entity
@Data
public class Item {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemid;
	private float mrp;
	private float discount;
	private float price;
	private String created_at;
	private String updated_at;
	
	@OneToOne
    @JoinColumn(name="productid")
    private Product product;
	
	@OneToOne
	@JoinColumn(name="brandid")
	private Brand brand;
	
	
	


}
