package com.tushar.swiggy.authorizationAssignment.repository;

import com.tushar.swiggy.authorizationAssignment.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Query(value = "Select p from UserInfo p where p.name = :username")
    UserInfo getUserByUsername(String username);
}
