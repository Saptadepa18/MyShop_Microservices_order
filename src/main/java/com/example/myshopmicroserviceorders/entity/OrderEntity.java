package com.example.myshopmicroserviceorders.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	
	private int userId;                
	
//	private int productId;
	private int quantity;
	private String status;
	private Date createdAt;
	private Date lastModifiedAt;
	
//	@Column(length=1000)
//	private List<Integer> productIds;
	
	private String productIds;

}
