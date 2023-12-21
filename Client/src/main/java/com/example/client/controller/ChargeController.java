package com.example.client.controller;

import com.example.client.GrpcClient;
import com.packt.modern.api.grpc.v1.CustomerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargeController {

    @Autowired
    private GrpcClient client;

    @GetMapping("/charges")
    public CustomerId.Response getSources(@RequestParam(defaultValue = "ab1ab2ab3ab4ab5") String customerId) {
        var req = CustomerId.newBuilder().setId(customerId).build();
        return client.getChargeServiceStub().retrieveAll(req);
    }

}