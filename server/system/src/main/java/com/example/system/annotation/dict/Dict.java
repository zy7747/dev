package com.example.system.annotation.dict;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Dict {
    //对应数据字典的code
    String code() default ""; // 字典

    //非字典翻译
    String apiCode() default "";
}
