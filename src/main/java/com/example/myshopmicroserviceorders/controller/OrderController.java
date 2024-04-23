package com.example.myshopmicroserviceorders.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myshopmicroserviceorders.entity.OrderEntity;
import com.example.myshopmicroserviceorders.service.OrderService;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowedHeaders="*")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping()
	public OrderEntity submitOrder(@RequestParam int userId,@RequestBody OrderEntity o) {
		return orderService.submitOrder(userId,o);
	}
	
	@GetMapping("/findByUserId")
	public List<OrderEntity> getOredrsByUserId(@RequestParam int Id)
	{
		return orderService.getOrdersByUserId(Id);
	}
	
	@PutMapping("/updateProduct/{productId}/updateAvailableCount/{quantityChange}")
	public ResponseEntity<String> updateProductAvailableCount(@PathVariable int productId,@PathVariable int quantityChange){
		orderService.updateProductAvailableCount(productId, quantityChange);
		return ResponseEntity.ok("product availability updated successfully");
	}
	
	@PutMapping("/{orderId}")
	public OrderEntity updateOrderStatus(@PathVariable int orderId,@RequestBody Map<String,String> requestBody)
	{
		String newStatus=requestBody.get("status");
		return orderService.updateOrderStatus(orderId, newStatus);
	}
	
	@GetMapping()
	public List<OrderEntity> getAllOrders()
	{
		return orderService.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public OrderEntity getOrderById(@PathVariable int id)
	{
		return orderService.getOrderById(id);
	}
	
	@GetMapping("/getOrderByStatus/{status}")
	public List<OrderEntity> getOrderByStatus(@PathVariable String status)
	{
		return orderService.getOrderByStatus(status);
	}

}
