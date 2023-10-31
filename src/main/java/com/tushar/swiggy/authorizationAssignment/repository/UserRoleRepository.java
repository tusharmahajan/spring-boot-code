package com.tushar.swiggy.authorizationAssignment.repository;

import com.tushar.swiggy.authorizationAssignment.models.UserRolesMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRolesMapping, Integer> {

    @Query(value = "Select p from UserRolesMapping p where p.userInfo.id = :userId")
    List<UserRolesMapping> getUserRolesByUserId(int userId);

}
