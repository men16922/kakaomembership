package com.bm.membership.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * packageName    : com.bm.membership.util
 * fileName       : DateUtilTest
 * author         : men16
 * date           : 2022-06-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-25        men16       최초 생성
 */
class DateUtilTest {

    @Test
    @DisplayName("날짜 변환 테스트")
    public void dateTest() throws ParseException {
        System.out.println(DateUtil.toLocalDateTime("20190110"));
        System.out.println(LocalDateTime.now());
    }


}