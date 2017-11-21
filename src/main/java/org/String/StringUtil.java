package org.String;

import org.Commonst;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class StringUtil {

    public static String buildKey(String... params) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        boolean flag=false;

        for (String param : params) {
            if (param.equals(null) || !param.equals("")) {
                stringBuffer.append(param).append(Commonst.SPLITOR);
            } else {
                flag=true;
                break;
            }
        }
        String str=stringBuffer.toString();
        int len = str.length();

        if (flag==true){
            throw new Exception("StringUtil.buildKey key值有空值");
        }else {
            return str.substring(0,len-6);
        }

    }


    public static String blankOrEmpty(String str,String str1){
        if (str==null || str.equals(Commonst.BLANK)){
            return str1;
        }else {
            return str;
        }
    }

    //科学计数法转字符串
    public static String scientificToString(String str){
        if(str==null || str.equals(Commonst.BLANK)){
            return "";
        }
        BigDecimal bigDecimal=new BigDecimal(str);
        return bigDecimal.toPlainString();

    }



    public static void main(String[] args) throws Exception {
//        System.out.println(StringUtil.buildKey("", ""));
        String s=new String("");
        StringUtil.blankOrEmpty(s,"d");
        System.out.println(s);
    }
}
