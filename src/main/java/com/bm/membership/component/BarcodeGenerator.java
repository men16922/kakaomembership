package com.bm.membership.component;

import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * packageName    : com.bm.membership.util
 * fileName       : UuidUtil
 * author         : men16
 * date           : 2022-06-22
 * description    : 바코드 생성용 컴포넌트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-22        men16       최초 생성
 */
@Component
public class BarcodeGenerator {

    private Object lock = new Object();

    public String createBarcode() {
        UUID uuid = null;

        synchronized (lock) {
            uuid = UUID.randomUUID();
        }
        return parseToShortUUID(uuid.toString());
    }

    //10자리의 UUID로 변환
    public String parseToShortUUID(String uuid) {
        int l = ByteBuffer.wrap(uuid.getBytes()).getInt();
        return Integer.toString(l, 9);
    }
}


