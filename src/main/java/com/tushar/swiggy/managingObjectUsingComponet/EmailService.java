package com.tushar.swiggy.managingObjectUsingComponet;

import org.springframework.stereotype.Component;

@Component
//@Primary
public class EmailService implements MessageService {

    @Override
    public void processMessage(String message) {
        System.out.println("Adding this in my email: " + message);
    }
}
