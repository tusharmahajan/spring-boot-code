package com.tushar.swiggy.authorizationAssignment.models.logicType;

import java.util.List;

public interface LogicType {

    boolean shouldGrantAccess(List<String> userPermissionList , List<String> apiPermissionList);
}
