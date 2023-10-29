package com.tushar.swiggy.jpaBasics.persistWithJPARepository;

import com.tushar.swiggy.jpaBasics.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p")
    List<Product> getAllProducts();

    @Query(value = "select p from Product p")
    List<Product> getAllProducts(Sort sortByName);

    @Query(value = "select p from Product p")
    Page<Product> getAllProductsInPaginatedManner(Pageable pageable);

    @Query(value = "select p from Product p where p.name = :name")
    List<Product> getProductByName(@Param("name") String name);


    @Query(value = "select * from product r Where r.name IN :names", nativeQuery = true)
    List<Product> getAllProductsWhereNamesAre(@Param("names") List<String> names);
}
