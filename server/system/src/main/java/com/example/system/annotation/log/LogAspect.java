package com.example.system.annotation.log;

import com.example.framework.utils.ServletUtils;
import com.example.system.dal.dto.operationLog.OperationLogSaveDTO;
import com.example.system.service.OperationLogService;
import com.example.system.utils.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    @Resource
    OperationLogService operationLogService;

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
        operationLog.setOperateIp(ServletUtils.getLoginUserIp());
        //物理地址
        String cityInfo = IPUtil.getCityInfo(ServletUtils.getLoginUserAdder());
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
