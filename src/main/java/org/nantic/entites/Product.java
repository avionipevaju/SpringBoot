package org.nantic.entites;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private int price;

	public Product() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}