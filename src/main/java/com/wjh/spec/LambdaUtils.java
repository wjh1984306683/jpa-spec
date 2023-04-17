package com.wjh.spec;

import com.wjh.spec.annotation.Getter;
import com.wjh.spec.annotation.Setter;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangjiahao
 * @since 2023/4/14 18:00
 */
public class LambdaUtils {

    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";

    public static SerializedLambda getSerializedLambda(Serializable fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            return (SerializedLambda) method.invoke(fn);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    public static String toLowerCaseFirstOne(String field) {
        if (Character.isLowerCase(field.charAt(0)))
            return field;
        else {
            char firstOne = Character.toLowerCase(field.charAt(0));
            String other = field.substring(1);
            return firstOne + other;
        }
    }

    public static <T, R> String convertToFieldName(Getter<T, R> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();

        if (methodName.startsWith(GETTER_PREFIX)) {
            return toLowerCaseFirstOne(methodName.replaceFirst(GETTER_PREFIX, ""));
        }
        return methodName;
    }

    public static <T, R> String convertToFieldName(Setter<T, R> fn) throws Exception {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        if (methodName.startsWith(SETTER_PREFIX)) {
            return toLowerCaseFirstOne(methodName.replaceFirst(SETTER_PREFIX, ""));
        }
        return methodName;
    }

}
