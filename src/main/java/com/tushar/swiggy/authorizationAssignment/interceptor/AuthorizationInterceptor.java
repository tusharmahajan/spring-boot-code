package com.tushar.swiggy.authorizationAssignment.interceptor;

import com.tushar.swiggy.authorizationAssignment.customAnnotations.Permission;
import com.tushar.swiggy.authorizationAssignment.models.*;
import com.tushar.swiggy.authorizationAssignment.models.logicType.LogicType;
import com.tushar.swiggy.authorizationAssignment.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PermissionRoleRepository permissionRoleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod;
        handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Permission.class)){
            List<String> apiPermissionList = Arrays.stream(method.getAnnotation(Permission.class)
                            .permissions()).map(PermissionsEnum::getVal).collect(Collectors.toList());

            List<String> userPermissionList = getUsersPermission(request);
            if(userPermissionList.isEmpty()) return false;

            Class<? extends LogicType> logicTypeClass = method.getAnnotation(Permission.class).type();
            LogicType logicType = logicTypeClass.getDeclaredConstructor().newInstance();
            return logicType.shouldGrantAccess(userPermissionList, apiPermissionList);
        }

        response.sendError(500, "Permission annotation required");
        return false;
    }

    private List<String> getUsersPermission(HttpServletRequest request) {
        String encodedCredentials = request.getHeader("authorization").split(" ")[1];

        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedString = new String(decodedBytes);

        String username = decodedString.split(":")[0];
        UserInfo userInfo = userInfoRepository.getUserByUsername(username);

        if(userInfo != null){
            // currently picking any one role of users and getting the permissions out of that.
            UserRolesMapping userRolesMapping = userRoleRepository.getUserRolesByUserId(userInfo.getId()).stream().findAny().get();
            Roles roles = userRolesMapping.getRoles();
            int roleId = roles.getId();
            return permissionRoleRepository.getPermissionsByRoleId(roleId)
                    .stream().map(s -> s.getPermissions().getName()).toList();
        }
        return Collections.emptyList();
    }

}
