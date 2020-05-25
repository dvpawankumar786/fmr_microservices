package com.example.giftcard;

import com.example.giftcard.command.api.IssueCmd;
import com.example.giftcard.command.api.RedeemCmd;
import com.example.giftcard.query.GiftcardSummary;
import com.example.giftcard.query.GiftcardSummaryQuery;
import com.thoughtworks.xstream.XStream;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GiftcardApplication {


    public static void main(String[] args) {
        SpringApplication.run(GiftcardApplication.class, args);
    }

    /*
     * The following is not really necessary for a demo app (and was not included
     * in the live demo), but it prevents an XStream security warning message.
     * In general, XStream serialization should usually be replaced by Jackson or
     * something else before going in production.
     */
    @Autowired
    public void config(Serializer serializer) {
        if (serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer) serializer).getXStream();
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[]{
                    "com.example.**",
                    "org.axonframework.**"});
        }
    }

	
}


