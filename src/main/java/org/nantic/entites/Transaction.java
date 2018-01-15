package org.nantic.entites;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String date;

	private int quantity;

	private String recieptId;

	private int total;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product")
	private Product productBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="customer")
	private User user;

	public Transaction() {
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRecieptId() {
		return this.recieptId;
	}

	public void setRecieptId(String recieptId) {
		this.recieptId = recieptId;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}