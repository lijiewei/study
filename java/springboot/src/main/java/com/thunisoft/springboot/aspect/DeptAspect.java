package com.thunisoft.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 部门操作切面控制
 * @Author: Administrator
 * @CreateDate: 2018/9/20 22:27
 */
@Aspect
@Component
public class DeptAspect {

    /** 日志. */
    private static final  Logger LOGGER = LoggerFactory.getLogger(DeptAspect.class);

    @Before("execution(public * com.thunisoft.springboot.controller.DeptController.*(..))")
    public void dealBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        LOGGER.info("url={}",request.getRequestURL());
        //请求方式
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("ip={}",request.getRemoteAddr());
        //类方法
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        LOGGER.info("args={}",joinPoint.getArgs());

    }

    @After("execution(public * com.thunisoft.springboot.controller.DeptController.*(..))")
    public void dealAfter(){
        LOGGER.info("listDept方法之后执行");
    }

    @AfterReturning(returning = "object",pointcut = "execution(public * com.thunisoft.springboot.controller.DeptController.*(..))")
    public void afterReturning(Object object){
        LOGGER.info("result={}",object);
    }
}
