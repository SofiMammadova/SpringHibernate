package az.perfect.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//t.k vse v vide packages, vizivaem:
@ComponentScan(basePackages = "az.perfect")
@PropertySource("db/database.properties") //ukazivaem put,chtobi ponal,otkuda beretsa dataSource

//t.k nado aktivirovat sozdanniy public PlatformTransactionManager hibernateTransactionManager
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DataSource dataSource(Environment env){ //podklucahemsa k database
        //Environment vibiraem iz springframework, a ne hibernate
        //dannie v skobkah berem iz YAML fayla
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName")); //berem iz yaml.fayla
        driverManagerDataSource.setUrl(env.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(env.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(env.getProperty("jdbc.password"));
        return driverManagerDataSource;

    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        //teper vovnutr nego nuzhno sdelat set
        localSessionFactoryBean.setDataSource(dataSource);
        //dolzhni pokazat, qde nashi entity nahodatsa(package):
        //t.k. oni nahodatsa v odnom package az.perfect:
        localSessionFactoryBean.setPackagesToScan("az.perfect.model");
        //ego delaem set na properties, no t.k properties net, nam nado ego sozdat vnizu
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;

    }

    @Bean
    //dla upravlenia tranzakciyami
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory); //sam budet delat inject, i tak on yavl @Bean'om
        return transactionManager;
    }

    /* sozdaem properties dla seta:
    t.e. eto nastroyka dla hiberneta
    Свойство hibernate.dialect заставляет Hibernate генерировать
    соответствующие операторы SQL для данной конкретной базы данных */
    private final Properties hibernateProperties(){
        //ishem v inete spring hibernateproperties();
        //https://www.baeldung.com/hibernate-5-spring
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "none");

        /*etu chast mi menaem na hibernate mysql dialect
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");*/

        //https://www.geeksforgeeks.org/hibernate-sql-dialects/
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");


        return hibernateProperties;

    }



}
