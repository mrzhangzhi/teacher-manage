package com.zt.manage.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.List;
import java.util.Map;

/**
 * json工具
 * @author
 */
public class JsonUtils {
    /**
     * 转换为JSON数据，指定转换属性
     *
     * @param map 实体类的 特定属性 映射
     * @param obj 转换对象
     * @return
     */
    public static String toJSONInclude(Map<Class, String[]> map, Object obj) {
        return toJSONExcludeOrInclude(map, obj, false);
    }

    /**
     * 转换为JSON数据，指定过滤属性
     *
     * @param map 实体类的 特定属性 映射
     * @param obj 转换对象
     * @return
     */
    public static String toJSONExclude(Map<Class, String[]> map, Object obj) {
        return toJSONExcludeOrInclude(map, obj, true);
    }

    /**
     * 转换成JSON时 提取或过滤 指定属性
     *
     * @param map       实体类的 特定属性 映射
     * @param obj       转换对象
     * @param isExclude 是否是过滤（true-过滤，false-提取）
     * @return
     */
    private static String toJSONExcludeOrInclude(final Map<Class, String[]> map, Object obj, final boolean isExclude) {
        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                //对象为空。直接放行
                if (source == null) {
                    return isExclude;
                }

                // 获取当前需要序列化的对象的类对象
                Class<?> clazz = source.getClass();

                // 无需序列的对象、寻找需要过滤的对象，可以提高查找层级
                // 找到不需要的序列化的类型
                for (Map.Entry<Class, String[]> item : map.entrySet()) {
                    // isAssignableFrom()，用来判断类型间是否有继承关系
                    if (item.getKey().isAssignableFrom(clazz)) {
                        String[] strs = item.getValue();

                        // 该类型下 此 name 值无需序列化
                        if (isHave(strs, name)) {
                            return !isExclude;
                        }
                    }
                }
                return isExclude;
            }

        };
        return JSON.toJSONString(obj, filter, SerializerFeature.DisableCircularReferenceDetect);
    }

    private static boolean isHave(String[] strs, String s) {

        for (int i = 0; i < strs.length; i++) {
            // 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
            if (strs[i].equals(s)) {
                // 查找到了就返回真，不在继续查询
                return true;
            }
        }

        // 没找到返回false
        return false;
    }


    /**
     * 将对象转为json串
     *
     * @param object 待转换对象
     * @return json串
     */
    public static String toJSONString(Object object) {
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
        return JSON.toJSONString(object, feature, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 将对象转为json串，支持features
     *
     * @param object   对象
     * @param features 特性
     * @return json String
     */
    public static String toJSONString(Object object, SerializerFeature... features) {
        return JSON.toJSONString(object, features);
    }

    public static String toJSONString(Object object, Class clazz, String... name) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, name);
        return JSON.toJSONString(object, filter, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 将json串解析为对象
     *
     * @param text  json字符串
     * @param <T>   返回实体
     * @param clazz 对象类型
     * @return 对象
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 将json字符串解析为对象
     *
     * @param text  json字符串
     * @param clazz 对象模型
     * @param <T>   返回实体类型
     * @return 对象
     */
    public static <T> List<T> parseList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
}
