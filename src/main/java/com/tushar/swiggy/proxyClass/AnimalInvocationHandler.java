package com.tushar.swiggy.proxyClass;

import com.tushar.swiggy.proxyClass.models.Animal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnimalInvocationHandler implements InvocationHandler {

    private Animal animal;

    public AnimalInvocationHandler(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Start invocation");
        method.invoke(animal, args);
        System.out.println("end invocation");
        return true;
    }
}
