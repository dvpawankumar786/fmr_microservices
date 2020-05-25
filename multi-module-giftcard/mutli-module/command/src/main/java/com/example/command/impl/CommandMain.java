package com.example.command.impl;

import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thoughtworks.xstream.XStream;


@SpringBootApplication
public class CommandMain{

    public static void main(String[] args) {
        SpringApplication.run(CommandMain.class, args);
    }
    @Autowired
    public void config(Serializer serializer) {
        if (serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer) serializer).getXStream();
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[]{
                    "com.example.command.**",
                    "org.axonframework.**"});
        }
    }
}
