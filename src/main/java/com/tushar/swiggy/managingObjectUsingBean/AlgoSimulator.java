package com.tushar.swiggy.managingObjectUsingBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tushar.swiggy")
public class AlgoSimulator implements ApplicationRunner {

    @Autowired
    SearchService searchService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @Bean
    public AlgoStrategy getSearcher(){
        return new AlgoStrategy(searchService);
    }
}
