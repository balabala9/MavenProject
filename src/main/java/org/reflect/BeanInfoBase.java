package org.reflect;

import com.alibaba.fastjson.JSON;
import org.bean.Student;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class BeanInfoBase {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student student=new Student();

        Map<String,Object> map=new HashMap<String,Object>();

        map.put("id","123");
        map.put("nickName","lala");

        BeanInfo beanInfo=Introspector.getBeanInfo(student.getClass());
        PropertyDescriptor[] propertyDescriptors= beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop:propertyDescriptors){
            if (map.containsKey(prop.getName())){

                prop.getWriteMethod().invoke(student,map.get(prop.getName()));
            }

        }

        System.out.println(JSON.toJSONString(student));
    }
}
