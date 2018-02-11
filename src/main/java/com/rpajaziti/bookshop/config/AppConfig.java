
package com.rpajaziti.bookshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Properties;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = {"com.rpajaziti.bookshop"})
@PropertySource(value = {"classpath:db.properties"})
public class AppConfig extends WebMvcConfigurationSupport {

    @Value("${spring.db.driverClassName}")
    private String dbDriverClassName;
    @Value("${spring.db.url}")
    private String dbUrl;
    @Value("${spring.db.username}")
    private String dbUsername;
    @Value("${spring.db.password}")
    private String dbPassword;

    @Bean("myDataSource")
    public ComboPooledDataSource myDataSource() throws PropertyVetoException {
        ComboPooledDataSource bean = new ComboPooledDataSource();
        bean.setDriverClass("com.mysql.cj.jdbc.Driver");
        bean.setJdbcUrl(dbUrl);
        bean.setUser(dbUsername);
        bean.setPassword(dbPassword);
        return bean;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(myDataSource());
        bean.setPackagesToScan("com.rpajaziti.bookshop");
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.show_sql", "true");
        bean.setHibernateProperties(prop);
        return bean;
    }

    @Bean("myTransactionManager")
    public HibernateTransactionManager myTransactionManager() throws PropertyVetoException {
        HibernateTransactionManager bean = new HibernateTransactionManager();
        bean.setSessionFactory(sessionFactory().getObject());
        return bean;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConverter());
        //addDefaultHttpMessageConverters(converters);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
