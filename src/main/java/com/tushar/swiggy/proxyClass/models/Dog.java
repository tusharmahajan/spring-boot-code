package com.tushar.swiggy.proxyClass.models;

public class Dog implements Animal{

    @Override
    public void introduce(String name) {
        System.out.println("I am " + name);
    }

    @Override
    public boolean canSpeak() {
        return false;
    }
}
