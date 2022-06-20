import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

import javax.annotation.PreDestroy;

/**
 * 부트 메인클래스
 */
@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@AutoConfigureAfter(JacksonAutoConfiguration.class)
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
