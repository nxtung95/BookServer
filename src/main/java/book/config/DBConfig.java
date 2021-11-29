package book.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("book.*")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
		"book.*"
})
public class DBConfig {

	@Bean
	public DataSource dataSource() {
		return mysqlDataSource();
	}

	private HikariDataSource mysqlDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/qlsanpham");
		config.setUsername("root");
		config.setPassword("root");
		config.setMaximumPoolSize(10);
		config.setAutoCommit(true);
		return new HikariDataSource(config);
	}

//	@Bean(name = "dataSourceTransactionManager")
//	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
//		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//		dataSourceTransactionManager.setDataSource(dataSource);
//
//		return dataSourceTransactionManager;
//	}

	@Bean
	public JpaTransactionManager jpaTransactionManager(@Autowired EntityManagerFactory emf) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("book.entity");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		factory.setPersistenceUnitName("default");
		factory.setJpaProperties(additionalProperties());
		return factory;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", true);
		return properties;
	}
}
