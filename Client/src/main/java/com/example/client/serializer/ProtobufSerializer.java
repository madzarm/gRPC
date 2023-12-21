package com.example.client.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;

public class ProtobufSerializer extends StdSerializer<MessageOrBuilder> {

    public ProtobufSerializer() {
        this(null);
    }

    public ProtobufSerializer(Class<MessageOrBuilder> t) {
        super(t);
    }

    @Override
    public void serialize(MessageOrBuilder value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        var printer = JsonFormat.printer().includingDefaultValueFields();
        String jsonString = printer.print(value);
        jsonGenerator.writeRawValue(jsonString);
    }


}
