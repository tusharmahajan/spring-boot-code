package com.tushar.swiggy.jpaBasics.persistWithJPARepository;

import com.tushar.swiggy.jpaBasics.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ProductJPARepositoryDemo implements ApplicationRunner {

    @Autowired
    ProductJPARepository productJPARepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Product bat = new Product("nicolas", "sports", "red");
//        productJPARepository.save(bat);
//        Sort sortByName = Sort.by("name");
//        getPaginatedData();
//        getProductByName();
    }

    private void getProductByName() {
        List<Product> products = productJPARepository.getProductByName("nicolas");
        for(Product product: products){
            System.out.println(product.getName());
        }
    }

    private void getPaginatedData() {

        Pageable productPage = Pageable.ofSize(2);
        Page<Product> productPageResult = productJPARepository.getAllProductsInPaginatedManner(productPage);

        while(true){
            List<Product> productList = productPageResult.getContent();
            if(productList.size() != 2) break;
            for(Product product: productList){
                System.out.println(product.getName());
            }
            System.out.println("--------");
            productPage = productPageResult.nextPageable();
            productPageResult = productJPARepository.getAllProductsInPaginatedManner(productPage);
        }
    }
}
