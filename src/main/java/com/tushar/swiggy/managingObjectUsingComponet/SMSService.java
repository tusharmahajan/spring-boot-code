package com.tushar.swiggy.managingObjectUsingComponet;

import org.springframework.stereotype.Component;

@Component
public class SMSService implements MessageService {

    @Override
    public void processMessage(String message) {
        System.out.println("Hi I am sms");
    }
}
