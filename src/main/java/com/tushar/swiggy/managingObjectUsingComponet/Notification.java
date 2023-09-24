package com.tushar.swiggy.managingObjectUsingComponet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Notification {

//    @Autowired
//    private MessageService emailService;  // using autowriring by name
//    @Autowired
    private MessageService messageService;

//    public Notification(@Autowired MessageService messageService) {       // using autowiring using constructor parameter
//        this.messageService = messageService;
//    }

    @Qualifier("emailService")    // autowiring using setters by qualifier
    @Autowired
    public void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }

//    @Autowired        // autowiring using setters by name
//    public void setMessageService(MessageService emailService){
//        this.messageService = emailService;
//    }

    public void sendMessage(String message){
        messageService.processMessage(message);
    }

}
