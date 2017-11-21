package org.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TranBM {


    public static Map<String, Object> tranTobean(Object obj) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor prop : propertyDescriptors) {

                if (!prop.getName().equals("class")) {

                    map.put(prop.getName(), prop.getReadMethod().invoke(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {

        }
        return map;
    }


    public static void beanTotran(Map map,Object obj){

        try{
            BeanInfo beanInfo=Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors= beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop:propertyDescriptors){
                if (map.containsKey(prop.getName())){

                    prop.getWriteMethod().invoke(obj,map.get(prop.getName()));
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
