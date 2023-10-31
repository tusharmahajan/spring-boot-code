package com.tushar.swiggy.authorizationAssignment.models.logicType;

import com.tushar.swiggy.authorizationAssignment.models.PermissionsEnum;

import java.util.List;

public class AnyLogicType implements LogicType {

    @Override
    public boolean shouldGrantAccess(List<String> userPermissionList, List<String> apiPermissionList) {

        for(String apiPermission: apiPermissionList){
            if(userPermissionList.contains(apiPermission)) return true;
        }
        return false;
    }
}
