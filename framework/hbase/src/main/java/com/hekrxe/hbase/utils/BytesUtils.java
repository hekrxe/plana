package com.hekrxe.hbase.utils;

import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @author tanhuayou on 2019/10/13
 */
public class BytesUtils {

    private static Logger logger = LoggerFactory.getLogger(BytesUtils.class);

    public static void decode(Object target, Field field, byte[] value) {
        try {
            Class<?> fieldType = field.getType();
            if (Long.class.equals(fieldType) || long.class.equals(fieldType)) {
                field.set(target, Bytes.toLong(value));
            } else if (Integer.class.equals(fieldType) || int.class.equals(fieldType)) {
                field.set(target, Bytes.toInt(value));
            } else if (Short.class.equals(fieldType) || short.class.equals(fieldType)) {
                field.set(target, Bytes.toShort(value));
            } else if (Double.class.equals(fieldType) || double.class.equals(fieldType)) {
                field.set(target, Bytes.toDouble(value));
            } else if (Float.class.equals(fieldType) || float.class.equals(fieldType)) {
                field.set(target, Bytes.toFloat(value));
            } else if (String.class.equals(fieldType)) {
                field.set(target, Bytes.toString(value));
            } else {
                logger.error("Unsupported Type");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static byte[] encode(Object target, Field field) {
        try {
            Object value = field.get(target);
            Class<?> fieldType = field.getType();
            if (null != value) {
                if ((Long.class == fieldType || long.class == fieldType) && value instanceof Long) {
                    return Bytes.toBytes((Long) value);
                } else if ((Integer.class == fieldType || int.class == fieldType) && value instanceof Integer) {
                    return Bytes.toBytes((Integer) value);
                } else if ((fieldType == Short.class || fieldType == short.class) && value instanceof Short) {
                    return Bytes.toBytes((Short) value);
                } else if (Double.class.equals(fieldType) && value instanceof Double) {
                    return Bytes.toBytes((Double) value);
                } else if (Float.class.equals(fieldType) && value instanceof Float) {
                    return Bytes.toBytes((Float) value);
                } else if (String.class.equals(fieldType) && value instanceof String) {
                    return Bytes.toBytes((String) value);
                } else {
                    throw new UnsupportedOperationException("数据类型");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
