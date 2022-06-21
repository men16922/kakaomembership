package com.bm.membership.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.bm.membership.component
 * fileName       : ApplicationContextComponent
 * author         : men16
 * date           : 2022-06-20
 * description    : ApplicationContext 주입용 Component
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@Component
public class ApplicationContextComponent {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;

    }

    /**
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}

