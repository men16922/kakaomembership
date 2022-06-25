package com.bm.membership.util;

import com.bm.membership.component.BarcodeGenerator;
import com.bm.membership.component.LockUUIDGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * packageName    : com.bm.membership.util
 * fileName       : BarcodeUtilTest
 * author         : men16
 * date           : 2022-06-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-22        men16       최초 생성
 */
@DisplayName("바코드 테스트")
class BarcodeUtilTest {


    @Test
    @DisplayName("바코드 생성 테스트")
    public void barcodeTest() {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
        System.out.println(barcodeGenerator.createBarcode());
    }

    @Test
    @DisplayName("uuid 생성 테스트")
    public void uuidTest() {
        LockUUIDGenerator lockUUIDGenerator = new LockUUIDGenerator();
        System.out.println(lockUUIDGenerator.generateUUID());
    }

}