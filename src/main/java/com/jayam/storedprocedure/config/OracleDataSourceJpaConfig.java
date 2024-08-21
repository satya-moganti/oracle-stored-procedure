package com.jayam.storedprocedure.config;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"com.jayam.storedprocedure.oracle.repo"},
  entityManagerFactoryRef = "oracleEntityManagerFactory",
  transactionManagerRef = "oracleTransactionManager"
)
public class OracleDataSourceJpaConfig{

    @Bean
    @ConfigurationProperties(prefix = "datasource.oracle")
    public DataSourceProperties oracleDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.oracle")
    public DataSource oracleDataSource() {
        return oracleDataSourceProperties().initializeDataSourceBuilder().build();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
      @Qualifier("oracleDataSource") DataSource oracleDataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(oracleDataSource)
          .packages("com.jayam.storedprocedure.oracle")
          .build();
    }

    @Bean
    public PlatformTransactionManager oracleTransactionManager(
      @Qualifier("oracleEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }




}
