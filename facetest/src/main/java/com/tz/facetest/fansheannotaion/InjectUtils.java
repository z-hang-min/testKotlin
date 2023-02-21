package com.tz.facetest.fansheannotaion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * created by zm on 2023/2/14
 *
 * @Description:
 */
class InjectUtils {
    public static void injectView(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> cls = activity.getClass();
//        cls.getField();//获得自己和父类的成员，不包含private
        Field[] declaredFields = cls.getDeclaredFields();//只获得自己的成员（不包含父类）所有作用域
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                int resId = injectView.value();
                View view = activity.findViewById(resId);
                //反射设置属性的值.
                field.setAccessible(true);//设置访问权限，允许操作private
                field.set(activity, view);
            }

        }
    }

    public static void injectVExtra(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> cls = activity.getClass();
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null)
            return;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectExtra.class)) {
                InjectExtra injectExtra = field.getAnnotation(InjectExtra.class);
                String key = TextUtils.isEmpty(injectExtra.value()) ? field.getName() : injectExtra.value();
                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);
                    Class<?> componentType = field.getType().getComponentType();
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                        Object[] objects = Arrays.copyOf(objs, objs.length,(Class< ? extends java.lang.Object[]>) field.getType());
                        obj = objects;
                    }
                    field.setAccessible(true);
                    field.set(activity, obj);
                }

            }
        }

    }
}
