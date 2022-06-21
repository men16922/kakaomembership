package com.bm.membership.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * packageName    : com.bm.membership.config
 * fileName       : DataSourceConfig
 * author         : men16
 * date           : 2022-06-20
 * description    : jpa 데이터베이스 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory"
        , transactionManagerRef="entityTransactionManager"
        , basePackages={"com.bm.membership.repository"})
public class DataSourceConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource datasource;

    /**
     * @param builder
     * @return
     */
    @Primary
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean configEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(datasource)
                .packages("com.bm.membership")
                .persistenceUnit("domain")
                .build();

    }


    /**
     * @param builder
     * @return
     */
    @Primary
    @Bean("entityTransactionManager")
    public PlatformTransactionManager configTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(configEntityManagerFactory(builder).getObject());

    }
}

