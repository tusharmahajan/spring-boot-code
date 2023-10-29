package com.tushar.swiggy.proxyClass;

import com.tushar.swiggy.proxyClass.models.Animal;
import com.tushar.swiggy.proxyClass.models.Dog;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

//@Component
public class ProxyClassDemo implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Dog dog = new Dog();
        ClassLoader animalClassLoader =  dog.getClass().getClassLoader();
        Class []interfaces =  dog.getClass().getInterfaces();

        Animal animalProxy = (Animal) Proxy.newProxyInstance(animalClassLoader, interfaces, new AnimalInvocationHandler(dog));
        animalProxy.introduce("Jojo");
    }

}
