package com.example.system.annotation.log;

import com.example.system.dal.dto.operationLog.OperationLogSaveDTO;
import com.example.system.service.OperationLogService;
import com.example.system.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Resource
    OperationLogService operationLogService;

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    @Pointcut("@annotation(com.example.system.annotation.log.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }


    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        // 获得注解
        Log controllerLog;

        OperationLogSaveDTO operationLog = new OperationLogSaveDTO();

        controllerLog = getAnnotationLog(joinPoint);

        if (controllerLog == null) {
            return;
        }

        //标题
        operationLog.setTitle(controllerLog.title());
        //模块
        operationLog.setModule(controllerLog.module());
        //内容
        operationLog.setContent(controllerLog.content());
        //IP
        HttpServletRequest request = getRequest();
        String ip = IPUtil.getIp(request);

        operationLog.setOperateIp(ip);
        //物理地址
        String cityInfo = null;
        try {
            cityInfo = IPUtil.getAdd(ip);
            log.info(ip);
            log.info(cityInfo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        operationLog.setOperatePlace(cityInfo);
        //类型
        operationLog.setType(controllerLog.type().toString());

        try {
            //抛出成功信息
            operationLog.setStatus("success");
            operationLog.setErrorMsg(jsonResult.toString());

        } catch (Exception exception) {
            exception.printStackTrace();
            //抛出异常信息
            operationLog.setStatus("error");
            operationLog.setErrorMsg(e.toString());
        }

        operationLogService.operationLogSave(operationLog);

    }

    /**
     * 获取注解上的信息
     */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
