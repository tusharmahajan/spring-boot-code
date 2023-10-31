package com.tushar.swiggy.authorizationAssignment.models.logicType;

import com.tushar.swiggy.authorizationAssignment.models.PermissionsEnum;

import java.util.List;

public class AllLogicType implements LogicType {

    @Override
    public boolean shouldGrantAccess(List<String> userPermissionList, List<String> apiPermissionList) {
        return userPermissionList.containsAll(apiPermissionList);
    }
}
