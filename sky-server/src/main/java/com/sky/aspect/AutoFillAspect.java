package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面，实现公共字段自动填充处理逻辑
 */
@Aspect
@Slf4j
@Component
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 设置前置通知，在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段的自动填充。。。");
        // 获取当前被拦截的数据库操作的类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 获取方法签名对象
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class); // 获取方法上的注解对象
        OperationType value = annotation.value();// 获取数据库操作类型

        // 获取当前被拦截的参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0)  return;
        Object entity = args[0];

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();

        // 为当前被拦截的公共参数赋值
        try {
            // 不论是插入还是修改都需要改变修改时间与修改人
            // 通过反射获取对象的方法
            Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            // 通过反射为对象赋值
            setUpdateTime.invoke(entity, now);
            setUpdateUser.invoke(entity, id);
            // 如果是插入则还需要添加创建人与创建时间
            if (value == OperationType.INSERT) {
                // 通过反射获取对象的方法
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                // 通过反射为对象赋值
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
