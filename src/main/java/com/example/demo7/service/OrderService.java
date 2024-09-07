package com.example.demo7.service;

import com.example.demo7.entity.StudentOrder;
import com.example.demo7.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private RazorpayClient razorpayClient;

    @Value("${razorpay.key.id}")
    private String razorpayId;
    @Value("${razorpay.secret.key}")
    private String razorpaySecret;

    //to create order in razorpay
    public StudentOrder createOrder(@NotNull StudentOrder studentOrder) throws RazorpayException {

        JSONObject orderReq = new JSONObject();
        orderReq.put("amount",studentOrder.getAmount()*100);
        orderReq.put("currency","INR");
        orderReq.put("receipt",studentOrder.getEmail()+studentOrder.getCourse());

        this.razorpayClient = new RazorpayClient(razorpayId,razorpaySecret);
        //to create razorpay order
        Order razorpayOrder = razorpayClient.orders.create(orderReq);

        studentOrder.setPaymentId(razorpayOrder.get("id"));
        studentOrder.setOrderStatus(razorpayOrder.get("status"));

        orderRepository.save(studentOrder);

        return studentOrder;
    }

    public void updateOrder(Map<String,String> responsePayLoad){
        String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
        StudentOrder studentOrder = orderRepository.findByPaymentId(razorPayOrderId);
        studentOrder.setOrderStatus("Payment_complected");

        orderRepository.save(studentOrder);

    }
}
