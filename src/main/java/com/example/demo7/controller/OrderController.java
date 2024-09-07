package com.example.demo7.controller;

import com.example.demo7.entity.StudentOrder;
import com.example.demo7.service.OrderService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping({"/",""})
    public String createOrderPage(){
        return "index";
    }

    @PostMapping(value = "/create-order", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StudentOrder> createOrder(@RequestBody StudentOrder studentOrder) throws RazorpayException {
        StudentOrder studentOrder1 = orderService.createOrder(studentOrder);
        return new ResponseEntity<>(studentOrder1, HttpStatus.CREATED);
    }

    @PostMapping("/payment-callback")
    public String handlePaymentCallback(@RequestParam Map<String,String> respnsePayload){
        System.out.println(respnsePayload);
        orderService.updateOrder(respnsePayload);
        return "paymentSuccess";
    }


}
