package com.example.system.annotation.dict;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class DictAspect {
    @Resource
    RedisUtils redisUtils;

    @Around("@annotation(dictUtil)")
    public Object around(ProceedingJoinPoint point, DictUtil dictUtil) throws Throwable {
        //1.获取到接口返回值
        Object resultR = point.proceed();


        //3.如果接口返回成功获取返回数据
        if (((Result) resultR).getCode() == 200) {
            Object resultData = ((Result) resultR).getData();
            //如果翻译类型是分页
            if (dictUtil.type().equals("page")) {
                //  获取分页内容
                PageList page = (PageList) resultData;
                if (page.getList().size() > 0) {
                    // 2.获取到所有需要翻译 的字典字段和名称
                    List<Object> resultList = getResult(page);

                    Map<String, String> dataMap = getDataMapping(resultList);
                    //4.将数据处理返回翻译后的List
                    List<Object> list = setDataMapping(resultList, dataMap);
                    page.setList(list);
                }


                //如果翻译类型是列表
            } else if (dictUtil.type().equals("list")) {
                // 2.获取到所有需要翻译 的字典字段和名称
                List list = (List) resultData;
                //判断是否有数据
                if (list.size() > 0) {
                    Map<String, String> dataMap = getDataMapping(list);

                    //4.将数据处理返回翻译后的List
                    List<Object> listData = setDataMapping(list, dataMap);
                    ((Result) resultR).setData(listData);
                }


            }
        }

        return resultR;
    }

    /**
     * 获取接口返回值内容
     */
    private List<Object> getResult(PageList page) {

        return page.getList();
    }


    /**
     * 获取需要翻译的字段和字段
     */
    private Map<String, String> getDataMapping(List<Object> result) {

        Field[] fields = result.get(0).getClass().getDeclaredFields();

        Map<String, String> map = new HashMap<>();

        for (Field field : fields) {
            Dict dataDict = field.getAnnotation(Dict.class);

            if (dataDict != null) {
                map.put(field.getName(), dataDict.code());
            }

        }

        return map;
    }

    /**
     * 从Redis获取所有字典
     */
    private JSONObject getDictMap() {
        String dictList = redisUtils.getRedis("dictMap");

        return JSON.parseObject(dictList);
    }

    /**
     * 获取接口返回值内容
     */
    private List<Object> setDataMapping(List<Object> resultList, Map<String, String> dataMap) {
        //1.从redis中获取所有字典集合
        JSONObject dictMap = getDictMap();
        //循环列表数据
        for (Object result : resultList) {
            //循环需要翻译的字段
            dataMap.forEach((key, value) -> {
                //找到需要翻译的字典
                JSONArray dictList = dictMap.getJSONArray(value);
                try {
                    //找到源数据的值
                    Field dataValueField = result.getClass().getDeclaredField(key);
                    //找到源数据的Name
                    Field dataNameField = result.getClass().getDeclaredField(key + "Name");
                    dataNameField.setAccessible(true);
                    dataValueField.setAccessible(true);
                    try {

                        String dataValue = dataValueField.get(result).toString();
                        //查找对应的翻译
                        if (dataValue != null) {
                            for (Object item : dictList) {

                                JSONObject dictObj = JSONObject.parseObject(item.toString());


                                try {
                                    if (dictObj.getString("value").equals(dataValue)) {
                                        dataNameField.set(result, dictObj.getString("label"));
                                    }

                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            });

        }


        return resultList;
    }
}