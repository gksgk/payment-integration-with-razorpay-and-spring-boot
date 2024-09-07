package com.example.demo7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_order")
public class StudentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String name;
    private String email;
    private String phoneNo;
    private String course;
    private int amount;
    private String orderStatus;
    private String paymentId;
}
//orderId, name, email, phoneNo, course, amount, orderStatus, paymentId

