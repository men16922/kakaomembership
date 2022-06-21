package com.bm.membership.util;


import com.bm.membership.component.JsonUserComponent;

/**
 * packageName    : com.bm.membership.util
 * fileName       : JsonUtil
 * author         : men16
 * date           : 2022-06-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public class JsonUtil {

    public static JsonUserComponent getJsonMapper() {
        return (JsonUserComponent)SpringBeanUtil.getBean(JsonUserComponent.class);

    }
}

