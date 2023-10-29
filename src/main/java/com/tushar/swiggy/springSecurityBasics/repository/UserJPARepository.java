package com.tushar.swiggy.springSecurityBasics.repository;

import com.tushar.swiggy.springSecurityBasics.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<Users, Integer> {

    @Query(value = "select p from Users p where p.username = :username1")
    Users getUserByUserName(@Param("username1") String username);

}
