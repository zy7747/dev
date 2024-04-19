package com.example.system.annotation.dict;

import cn.hutool.core.util.ObjectUtil;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class DictAspect {
    public static final String suffix = "Name";

    @Pointcut("@annotation(dictClass)")
    public void dictPointCut(DictClass dictClass) {
    }

    @Around("@annotation(dictClass)")
    public Object around(ProceedingJoinPoint point, DictClass dictClass) throws Throwable {
        //截取需要返回的数据
        Object resultR = point.proceed();

        //判断是否有返回语句
        if (ObjectUtil.isNull(resultR)) {
            return resultR;
        }
        // 如果有返回语句
        Object resultData = ((Result) resultR).getData();

        //如果是分页
        if (resultData instanceof PageList) {
            Object list = ((PageList) resultData).getList();
            Object obj;

            List res = ((List) list);

            if (res.size() == 0) {
                return res;
            } else {
                obj = res.get(0);
            }
            getDictMap(obj.getClass());

        }

        return resultR;
    }


    public Map<String, String> getDictMap(Class vo) {
        Field[] fields = vo.getDeclaredFields();

        HashMap dictMap = new HashMap();

        for (Field field : fields) {
            Dict DictData;

            DictData = field.getAnnotation(Dict.class);

            if (DictData != null) {
                dictMap.put(field.getName(), DictData.code());
            }
        }

        return dictMap;
    }
}