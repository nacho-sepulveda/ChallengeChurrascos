package com.ProductAPI.app.DTOs;

import java.io.Serializable;




public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Long SKU;
	private int code;
	private String name;
	private String description;
	private Double price;
	private String currency;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getSKU() {
		return SKU;
	}
	public void setSKU(Long sKU) {
		SKU = sKU;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
