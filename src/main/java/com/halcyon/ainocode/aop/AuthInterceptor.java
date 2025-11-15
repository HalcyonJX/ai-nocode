package com.halcyon.ainocode.aop;

import com.halcyon.ainocode.annotation.AuthCheck;
import com.halcyon.ainocode.exception.ErrorCode;
import com.halcyon.ainocode.exception.ThrowUtils;
import com.halcyon.ainocode.model.entity.User;
import com.halcyon.ainocode.model.enums.UserRoleEnum;
import com.halcyon.ainocode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable{
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //不需要权限，放行
        if (mustRoleEnum == null){
            return joinPoint.proceed();
        }
        //以下必须有权限才通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        ThrowUtils.throwIf(userRoleEnum == null, ErrorCode.NOT_LOGIN_ERROR);
        //要求必须有管理员权限，但用户没有，拒绝
        ThrowUtils.throwIf(
                UserRoleEnum.ADMIN.equals(mustRoleEnum) &&
                        !UserRoleEnum.ADMIN.equals(userRoleEnum),
                ErrorCode.NO_AUTH_ERROR
        );
        return joinPoint.proceed();
    }
}
