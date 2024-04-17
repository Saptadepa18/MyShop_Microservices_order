package com.example.myshopmicroserviceorders.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.myshopmicroserviceorders.config.UrlConfig;
import com.example.myshopmicroserviceorders.entity.OrderEntity;
import com.example.myshopmicroserviceorders.exception.NoOrderFoundException;
import com.example.myshopmicroserviceorders.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private UrlConfig urlConfig;
	
//	private final String PRODUCT_SERVICE_URL= "http://localhost:8083/";
	
	public void updateProductAvailableCount(int productId,int quantityChange)
	{
		restTemplate.put(urlConfig.PRODUCT_SERVICE_URL+"/product/updateProduct/"+productId+"/updateAvailableCount/"+quantityChange,null);
	}
	
	public OrderEntity submitOrder(int userId,OrderEntity o)
	{
		o.setUserId(userId);
		o.setCreatedAt(new Date());
		o=orderRepo.save(o);
//		for(it i=o;i<o.getProductIds().)
		String[] productIdsString=o.getProductIds().split(",");
		for(String productId: productIdsString) {
		updateProductAvailableCount(Integer.parseInt(productId),o.getQuantity());
		}
		return o;
	}

	public List<OrderEntity> getAllOrders()
	{	
		return (List<OrderEntity>)orderRepo.findAll();
	}

	public OrderEntity getOrderById(int id) {
		if(orderRepo.findById(id).isPresent()) {
		return orderRepo.findById(id).get();
		}
		else
		{
			throw new NoOrderFoundException("No Order Found");
		}
	}
	

	public OrderEntity updateOrderStatus(int orderId, String newStatus) {
		OrderEntity order=orderRepo.findById(orderId).orElse(null);
		if(order!=null) {
			order.setStatus(newStatus);
			order.setLastModifiedAt(new Date());
			order=orderRepo.save(order);
			
			if(newStatus.equalsIgnoreCase("cancelled")) {
				System.out.println("iside cancelled");
				String[] productIdsString=order.getProductIds().split(",");
				for(String productId: productIdsString) {
				updateProductAvailableCount(Integer.parseInt(newStatus),-order.getQuantity());
				}
				
			}
			return order;
		}
		throw new NoOrderFoundException("No Order Found");
		
	}

	
	public List<OrderEntity> getOrderByStatus(String status) {
		return orderRepo.findByStatus(status);
	
	}

	
	public List<OrderEntity> getOrdersByUserId(int id) {
		List<OrderEntity> orders=orderRepo.findByUserId(id);
		System.out.println(orders);
		if(!orders.isEmpty())
		{
			return orders;
		}
		else
		{
			throw new NoOrderFoundException("No Order Found with this User id");
		}
		
	}
		
}
