package com.tushar.swiggy.authorizationAssignment.customAnnotations;

import com.tushar.swiggy.authorizationAssignment.models.logicType.AllLogicType;
import com.tushar.swiggy.authorizationAssignment.models.logicType.LogicType;
import com.tushar.swiggy.authorizationAssignment.models.PermissionsEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    PermissionsEnum[] permissions() default {};
    Class<? extends LogicType> type() default AllLogicType.class;
}
