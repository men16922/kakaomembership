package com.bm.membership.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * packageName    : com.bm.membership.util
 * fileName       : ParamUtil
 * author         : men16
 * date           : 2022-06-22
 * description    : 타입 캐스팅 유틸
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-22        men16       최초 생성
 */
public class ParamUtil {

    /**
     * @param value
     * @return
     */
    public static float toFloat(Object value) {
        return NumberUtils.toFloat(str(value));

    }


    /**
     * @param value
     * @return
     */
    public static int toInt(Object value) {
        return NumberUtils.toInt(str(value));

    }


    /**
     * @param value
     * @return
     */
    public static String str(Object value) {
        return str(value, null);

    }


    /**
     * @param value
     * @param defaultValue
     * @return
     */
    public static String str(Object value, String defaultValue) {
        String ret = null;

        if(value == null) {
            return defaultValue;

        }

        ret = String.valueOf(value);

        if(StringUtils.isBlank(ret)) {
            return defaultValue;

        } else {
            return ret;

        }

    }
}

