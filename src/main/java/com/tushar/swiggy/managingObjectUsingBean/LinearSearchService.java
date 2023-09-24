package com.tushar.swiggy.managingObjectUsingBean;

import org.springframework.stereotype.Component;

@Component
public class LinearSearchService implements SearchService{

    @Override
    public int search() {
        System.out.println("Linear searcher");
        return 0;
    }
}
