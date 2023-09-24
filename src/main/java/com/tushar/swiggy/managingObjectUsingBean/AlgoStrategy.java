package com.tushar.swiggy.managingObjectUsingBean;


import org.springframework.beans.factory.annotation.Autowired;

public class AlgoStrategy {

    private SearchService searchService;

    public AlgoStrategy(SearchService searchService) {
        this.searchService = searchService;
    }

    public void searchData(){
        searchService.search();
    }
}
