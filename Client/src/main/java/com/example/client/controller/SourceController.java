package com.example.client.controller;

import com.example.client.GrpcClient;
import com.packt.modern.api.grpc.v1.CreateSourceReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

    @Autowired
    private GrpcClient grpcClient;

    @PostMapping("/sources")
    public CreateSourceReq.Response createSource(@RequestBody CreateSourceReq req) {
        return grpcClient.getSourceServiceStub().create(req);
    }
}
