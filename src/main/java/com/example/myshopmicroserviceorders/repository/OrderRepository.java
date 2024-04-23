package com.example.myshopmicroserviceorders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.myshopmicroserviceorders.entity.OrderEntity;



@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer>{
	
	@Query("SELECT p FROM OrderEntity p WHERE p.status = :status")
    List<OrderEntity> findByStatus(@Param("status") String status);

	@Query("SELECT or FROM OrderEntity or WHERE or.userId=:userId")
	List<OrderEntity> findByUserId(@Param("userId")int userId);


}
