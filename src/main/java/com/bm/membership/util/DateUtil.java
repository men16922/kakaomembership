package com.bm.membership.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * packageName    : com.bm.membership.util
 * fileName       : DateUtil
 * author         : men16
 * date           : 2022-06-22
 * description    : 날짜 관련 유틸
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-22        men16       최초 생성
 */
@Slf4j
public class DateUtil {

    private final static DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static String dateFormat(LocalDateTime localDateTime) {
        return localDateTime.format(datetimeFormat);
    }

    public static LocalDateTime toLocalDateTime(String strDate) throws ParseException {

        return simpleDateFormat.parse(strDate).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}

