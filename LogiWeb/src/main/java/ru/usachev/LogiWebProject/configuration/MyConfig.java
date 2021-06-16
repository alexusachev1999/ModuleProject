package ru.usachev.LogiWebProject.configuration;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.usachev.LogiWebProject.converter.StringToWaypointDAO;
import ru.usachev.LogiWebProject.dto.WaypointDTO;
import ru.usachev.LogiWebProject.entity.Waypoint;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "ru.usachev.LogiWebProject.**")
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ SecurityConfig.class})
public class MyConfig extends WebMvcConfigurationSupport {


    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/logiWeb?useSSL=false&amp&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("cfif270599!");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.usachev.LogiWebProject.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.connection.Charset", "UTF-8");
        hibernateProperties.setProperty("hibernate.connection.CharacterEncoding", "UTF-8");
        hibernateProperties.setProperty("hibernate.connection.Useunicode", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToWaypointDAO());
    }
}
