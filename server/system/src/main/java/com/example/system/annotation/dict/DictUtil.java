package com.example.system.annotation.dict;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DictUtil {
    //对应数据字典的code
    String type() default ""; // 接口类型
}

