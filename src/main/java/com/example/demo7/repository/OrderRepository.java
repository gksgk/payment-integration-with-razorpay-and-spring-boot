package com.example.demo7.repository;

import com.example.demo7.entity.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<StudentOrder,Integer> {
    public StudentOrder findByPaymentId(String paymentId);
}
