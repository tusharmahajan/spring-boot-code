package com.tushar.swiggy.managingObjectUsingBean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BinarySearchService implements SearchService{

    @Override
    public int search() {
        System.out.println("Binary Searcher");
        return 0;
    }
}
