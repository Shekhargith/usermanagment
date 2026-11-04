package com.usermanagement.DBConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.usermanagement.repository.shahiWeb", entityManagerFactoryRef = "shahiWebEntityManagerFactory", transactionManagerRef = "shahiWebTransactionManager")
public class ShahiwebConfig {

	@Bean(name = "shahiWebDataSource")
	@ConfigurationProperties(prefix = "spring.shahiweb.datasource")
	DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "shahiWebEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("shahiWebDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.usermanagement.entities.shahiWeb").persistenceUnit("shahiWeb")
				.build();
	}

	@Bean(name = "shahiWebTransactionManager")
	PlatformTransactionManager transactionManager(
			@Qualifier("shahiWebEntityManagerFactory") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean(name = "shahiWebTemplate")
	JdbcTemplate jdbcTemplate(@Qualifier("shahiWebDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean(name = "shahiWebClient")
	JdbcClient jdbcClient(@Qualifier("shahiWebDataSource") DataSource ds) {
		return JdbcClient.create(ds);

	}

}
