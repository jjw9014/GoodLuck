package com.help.server.common;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class CommonUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static List<List> splitList(List sourceList, int maxSize) {
        List<List> listList = new ArrayList<List>();
        if (sourceList.size() != 0) {
            int size = sourceList.size();
            int step = maxSize;
            int times = size/step + ((size % step == 0) ? 0 : 1);
            for (int idx = 0; idx < times; idx++) {
                int fromIndex = step * idx;
                int toIndex = (times - 1 == idx) ? size : (step + fromIndex);
                List<String> subList = sourceList.subList(fromIndex, toIndex);
                String[] srcStringArray = subList.toArray(new String[0]);
                String[] destStringArray = new String[srcStringArray.length];
                System.arraycopy(srcStringArray, 0, destStringArray, 0, srcStringArray.length);
                List<String> list = Arrays.asList(destStringArray);
                listList.add(list);
            }
        }

        return listList;
    }

    public static List subtract(List aList, List bList) {
        if (CollectionUtils.isEmpty(aList) || CollectionUtils.isEmpty(bList)) {
            return aList;
        }

        aList.removeAll(bList);
        return aList;
    }

    public static List add(List aList, List bList) {
        if (CollectionUtils.isEmpty(aList)) {
            return bList;
        }

        if (CollectionUtils.isEmpty(bList)) {
            return aList;
        }

        List cList = new ArrayList(bList);
        cList.retainAll(aList);
        bList.removeAll(cList);
        aList.addAll(bList);
        return aList;
    }

    public static String list2String(List aList) {
        if (CollectionUtils.isEmpty(aList)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Object obj : aList) {
            sb.append(",").append(obj.toString());
        }

        return sb.substring(1);
    }

    public static String list2StringWithQuote(List list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Object str : list) {
            sb.append(String.format("'%s',", str.toString()));
        }
        sb.setLength(sb.length()-1);

        return sb.toString();
    }

    public static List string2List(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        List<String> list = new ArrayList();
        String [] edinfoList = str.split(",");

        for(String item : edinfoList){
            list.add(item);
        }

        return list;
    }

    public static void assertLessThanField(int value, int maxValue, ResultCodeEnum resultCodeEnum) {
        if (value < maxValue) {
            throw new ResultException(resultCodeEnum);
        }
    }

    public static void assertMoreThanField(int value, int threshod, ResultCodeEnum resultCodeEnum) {
        if (value > threshod) {
            throw new ResultException(resultCodeEnum);
        }
    }

    public static void assertEmptyField(Object value, ResultCodeEnum resultCodeEnum) {
        if (isEmpty(value)) {
            throw new ResultException(resultCodeEnum);
        }
    }

    private static boolean isEmpty(Object value) {
        if (value instanceof Collection) {
            if (CollectionUtils.isEmpty((Collection)value)) {
                return true;
            }
        } else if (value instanceof Map) {
            if (MapUtils.isEmpty((Map)value)) {
                return true;
            }
        } else if (value instanceof String) {
            if (StringUtils.isBlank((String)value)) {
                return true;
            }
        } else {
            return isNullField(value);
        }

        return false;
    }

    public static void assertNullField(Object value, ResultCodeEnum resultCodeEnum) {
        assertNullField(value, null, resultCodeEnum);
    }

    public static void assertNullField(Object value, Object nullValue, ResultCodeEnum resultCodeEnum) {
        if (isNullField(value, nullValue)) {
            throw new ResultException(resultCodeEnum);
        }
    }

    public static boolean isNullField(Object value) {
        return isNullField(value, null);
    }

    public static boolean isNullField(Object value, Object nullValue) {
        if (value == null) {
            return true;
        }
        if (nullValue != null && nullValue.equals(value)) {
            return true;
        }

        return false;
    }

    public static String[] combinStringArray(String[] array1, String... array2) {
        if (array1 == null || array2 == null) {
            return (array1 != null) ? array1 : array2;
        }

        String[] array = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, array, 0, array1.length);
        System.arraycopy(array2, 0, array, array1.length, array2.length);

        return array;
    }

    public static Object combin(Object obj, Object... objs) {
        if (objs == null || objs.length == 0) {
            return obj;
        }

        try {
            for (int idx = 0; idx < objs.length; idx++) {
                Object combinObject = objs[idx];
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Object value = field.get(combinObject);
                    if (value != null) {
                        field.set(obj, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static String getDefaultIfNull(String obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }

        return obj;
    }

    public static BigDecimal getDefaultIfNull(BigDecimal obj, BigDecimal defaultValue) {
        if (obj == null) {
            return defaultValue;
        }

        return obj;
    }

    public static Integer getDefaultIfNull(Integer obj, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }

        return obj;
    }

    public static String getDefaultIfBlank(String obj, String defaultValue) {
        if (StringUtils.isBlank(obj)) {
            return defaultValue;
        }

        return obj;
    }


    private static void print(List<String> list) {
        System.out.print("[");
        for (String str : list) {
            System.out.print(str + ",");
        }
        System.out.print("]");
        System.out.println();
    }

    public static Object getNullIfNoChange(Object updateValue, Object currentValue) {
        if (updateValue == null) {
            return null;
        }

        if (!updateValue.equals(currentValue)) {
            return updateValue;
        }

        return null;
    }

    public static void main (String[] args) {
        CommonUtils commonUtils = new CommonUtils();
//        commonUtils.testSplitList();
//        commonUtils.testCombin();
    }


}

