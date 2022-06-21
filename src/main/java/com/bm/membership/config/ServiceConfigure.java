package com.bm.membership.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * packageName    : com.bm.membership.config
 * fileName       : ServiceConfigure
 * author         : men16
 * date           : 2022-06-20
 * description    : 서비스에 필요한 빈 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

@Slf4j
@Configuration
public class ServiceConfigure {

    @Value("${spring.datasource.poolname}")
    public String poolName;

    @Value("${spring.datasource.min-idle}")
    public int minIdle;

    @Value("${spring.datasource.max-pool}")
    public int maxPool;

    @Primary
    @Bean(name="dataSource")
    @Qualifier("dataSource")
    public DataSource dataSource(DataSourceProperties properties) {
        DataSourceBuilder<?> factory = DataSourceBuilder
                .create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());

        log.info("JDBC DRIVER :" + properties.getDriverClassName());
        HikariDataSource dataSource = (HikariDataSource) factory.build();
        dataSource.setPoolName(poolName);
        dataSource.setMinimumIdle(minIdle);
        dataSource.setMaximumPoolSize(maxPool);
        return dataSource;
    }
}

