package com.tushar.swiggy.springSecurityBasics.repository;

import com.tushar.swiggy.springSecurityBasics.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends CrudRepository<Users, Integer> {
}
