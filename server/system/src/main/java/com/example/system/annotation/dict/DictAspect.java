package com.example.system.annotation.dict;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class DictAspect {
    public static final String suffix = "_Name";

    @Pointcut("@annotation(dict)")
    public void logPointCut() {

    }

    @Before("@annotation(dict)")
    public void translateDict(JoinPoint joinPoint, Dict dict) {
        log.info("拿到字典了");
    }
}