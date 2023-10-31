package com.tushar.swiggy.authorizationAssignment.controller;

import com.tushar.swiggy.authorizationAssignment.customAnnotations.Permission;
import com.tushar.swiggy.authorizationAssignment.models.PermissionsEnum;
import com.tushar.swiggy.authorizationAssignment.models.logicType.AllLogicType;
import com.tushar.swiggy.authorizationAssignment.models.logicType.AnyLogicType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authorize")
public class RolesPermissionController {

    @Permission(permissions = {PermissionsEnum.ALLOW_READ, PermissionsEnum.ALLOW_WRITE}, type = AllLogicType.class)
    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, @RequestParam(value = "num") Optional<Integer> num) {
        System.out.println(num);
        return "hello";
    }

}
