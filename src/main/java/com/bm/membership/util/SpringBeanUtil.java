package com.bm.membership.util;

import com.bm.membership.component.ApplicationContextComponent;
import org.springframework.context.ApplicationContext;

/**
 * packageName    : com.bm.membership.util
 * fileName       : SpringBeanUtil
 * author         : men16
 * date           : 2022-06-20
 * description    : Spring Bean 인스턴스 접근용 Util 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public class SpringBeanUtil {

    /**
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextComponent.getApplicationContext();
        return applicationContext.getBean(beanName);

    }

    /**
     * @param classType
     * @return
     */
    public static Object getBean(Class<?> classType) {
        ApplicationContext applicationContext = ApplicationContextComponent.getApplicationContext();
        return applicationContext.getBean(classType);
    }
}

