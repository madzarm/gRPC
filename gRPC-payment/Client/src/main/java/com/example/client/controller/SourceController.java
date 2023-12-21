package com.example.client.controller;

import com.example.client.GrpcClient;
import com.packt.modern.api.grpc.v1.CreateSourceReq;
import com.packt.modern.api.grpc.v1.SourceId;
import com.packt.modern.api.grpc.v1.UpdateSourceReq;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/sources/{id}")
    public SourceId.Response retrieveSource(@PathVariable("id") String id) {
        var sourceId = SourceId.newBuilder().setId(id).build();
        return grpcClient.getSourceServiceStub().retrieve(sourceId);
    }

    @PatchMapping("/sources")
    public UpdateSourceReq.Response updateSource(@RequestBody UpdateSourceReq req) {
        return grpcClient.getSourceServiceStub().update(req);
    }
}
