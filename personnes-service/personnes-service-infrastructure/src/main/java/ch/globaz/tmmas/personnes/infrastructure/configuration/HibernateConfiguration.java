package ch.globaz.tmmas.personnes.infrastructure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	@Value("${hibernate.driver-class-name}")
	private String DRIVER;

	@Value("${hibernate.url}")
	private String URL;

	@Value("${hibernate.username}")
	private String USERNAME;

	@Value("${hibernate.password}")
	private String PASSWORD;

	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${hibernate.show.sql}")
	private String SHOW_SQL;

	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties(prefix = "datasource.activiti")
	public DataSource activitiDataSource() {

		System.out.println();

		return DataSourceBuilder
				.create()
				.url(URL)
				.username(USERNAME)
				.password(PASSWORD)
				.driverClassName(DRIVER)
				.build();
	}



	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}


	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(activitiDataSource());
		sessionFactory.setPackagesToScan(new String[] { "ch.globaz.springbackend" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return sessionFactory;
	}



	private final Properties hibernateProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		hibernateProperties.setProperty("hibernate.dialect", DIALECT);
		hibernateProperties.setProperty("databasePlatform", DIALECT);
		hibernateProperties.setProperty("hibernate.show_sql", SHOW_SQL);


		return hibernateProperties;
	}
}
