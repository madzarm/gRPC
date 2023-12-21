package com.example.client.config;

import com.example.client.deserializer.ProtobufDeserializer;
import com.example.client.serializer.ProtobufSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.protobuf.MessageOrBuilder;
import com.packt.modern.api.grpc.v1.CreateSourceReq;
import com.packt.modern.api.grpc.v1.UpdateSourceReq;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(MessageOrBuilder.class, new ProtobufSerializer());
        module.addDeserializer(CreateSourceReq.class, new ProtobufDeserializer<>(CreateSourceReq.class));
        module.addDeserializer(UpdateSourceReq.class, new ProtobufDeserializer<>(UpdateSourceReq.class));
        mapper.registerModule(module);
        return mapper;
    }
}
