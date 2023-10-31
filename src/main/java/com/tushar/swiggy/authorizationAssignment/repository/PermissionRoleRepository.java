package com.tushar.swiggy.authorizationAssignment.repository;

import com.tushar.swiggy.authorizationAssignment.models.PermissionRolesMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRoleRepository extends JpaRepository<PermissionRolesMapping, Integer> {

    @Query(value = "Select p from PermissionRolesMapping p where p.roles.id = :roleId")
    List<PermissionRolesMapping> getPermissionsByRoleId(int roleId);
}
