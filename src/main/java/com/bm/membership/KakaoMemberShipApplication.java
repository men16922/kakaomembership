package com.bm.membership;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PreDestroy;

/**
 * 부트 메인클래스
 */
@Slf4j
@SpringBootApplication
@EnableCaching
public class KakaoMemberShipApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoMemberShipApplication.class, args);
    }

    @PreDestroy
    public void destroy() {
        try {
            log.info("SHUTDOWN COMPLETE.");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
