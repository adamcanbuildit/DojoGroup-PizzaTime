package com.dojogroup.pizzatime.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	@NotBlank
	private String deliveryMethod;
	@NotBlank
	private String pizzaSize;
	@NotBlank
	private String crustType;
	@NotBlank
	private Long qty;
	//private List<String> toppings;
	@ElementCollection(targetClass=String.class)
	private List<String> toppings;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="favoritedBy_id")
	private User favoritedBy;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	
	//Constructors
	public Order() {
		super();
		}
	public Order(User user, @NotBlank String deliveryMethod, @NotBlank String pizzaSize, @NotBlank String crustType,
			@NotEmpty Long qty, List<String> toppings) {
		super();
		this.user = user;
		this.deliveryMethod = deliveryMethod;
		this.pizzaSize = pizzaSize;
		this.crustType = crustType;
		this.qty = qty;
		this.toppings = toppings;
	}

	//Getters & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public String getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public String getCrustType() {
		return crustType;
	}
	public void setCrustType(String crustType) {
		this.crustType = crustType;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public List<String> getToppings() {
		return toppings;
	}
	public void setToppings(List<String> toppings) {
		this.toppings = toppings;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	//Timestamps
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
