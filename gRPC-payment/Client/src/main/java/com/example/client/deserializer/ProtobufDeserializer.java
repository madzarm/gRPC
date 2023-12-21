package com.example.client.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;

public class ProtobufDeserializer<T extends Message> extends StdDeserializer<T> {

    private final Class<T> clazz;

    public ProtobufDeserializer(Class<T> clazz) {
        super(clazz);
        this.clazz = clazz;
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String json = jsonParser.readValueAsTree().toString();
        JsonFormat.Parser parser = JsonFormat.parser();
        Message.Builder builder = null;
        try {
            builder = (Message.Builder) clazz.getMethod("newBuilder").invoke(null);
            parser.merge(json, builder);
        } catch (ReflectiveOperationException e) {
            throw new IOException("Failed to deserialize protobuf message", e);
        }
        return (T) builder.build();
    }
}