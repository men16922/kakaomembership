package com.bm.membership.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.bm.membership.common.enums
 * fileName       : BusinessType
 * author         : men16
 * date           : 2022-06-20
 * description    : 상점 업종 enum
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public enum Category {
    A("GROCERY"), B("COSMETIC"), C("RESTAURANT");

    final private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    private Category(String categoryId) {
        this.categoryId = categoryId;
    }


    private static final Map<String, String> categoryList;

    static {
        categoryList = new HashMap<String, String>();
        for (Category category : values()) {
            categoryList.put(category.name(), category.getCategoryId());
        }
    }

    public static String findBy(String arg) {
        return categoryList.get(arg);
    }
}
