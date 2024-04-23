package com.example.myshopmicroserviceorders.service;

import java.util.List;

import com.example.myshopmicroserviceorders.entity.OrderEntity;

public interface OrderService {
	
	public OrderEntity submitOrder(int userId,OrderEntity o);
	public void updateProductAvailableCount(int productId,int quantityChange);
	public OrderEntity updateOrderStatus(int orderId,String newStatus);
	public List<OrderEntity> getAllOrders();
	public OrderEntity getOrderById(int id);
	public List<OrderEntity> getOrderByStatus(String status);
	public List<OrderEntity> getOrdersByUserId(int id);

}
