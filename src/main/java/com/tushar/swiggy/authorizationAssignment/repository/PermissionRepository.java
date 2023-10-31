package com.tushar.swiggy.authorizationAssignment.repository;


import com.tushar.swiggy.authorizationAssignment.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Integer> {
}
