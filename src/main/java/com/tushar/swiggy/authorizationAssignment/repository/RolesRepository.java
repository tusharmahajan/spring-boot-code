package com.tushar.swiggy.authorizationAssignment.repository;

import com.tushar.swiggy.authorizationAssignment.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
