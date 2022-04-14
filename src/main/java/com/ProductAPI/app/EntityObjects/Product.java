package com.ProductAPI.app.EntityObjects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "Id")
	private Integer id;
	@Column(name = "SKU", nullable = false)
	private Long SKU;
	@Column(name = "code", nullable = false)
	private int code;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price", nullable = false)
	private Double price;
	@Column(name = "currency")
	private String currency;
	/*@Column(name = "picture")
	private Picture picture;*/
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
	@Override
	public String toString() {
		return "Product [id=" + id + ", SKU=" + SKU + ", code=" + code + ", name=" + name + ", description="
				+ description + ", price=" + price + ", currency=" + currency + "]";
	}
	
}
