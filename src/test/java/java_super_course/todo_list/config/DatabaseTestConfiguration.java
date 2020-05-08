//package java_super_course.todo_list.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import liquibase.integration.spring.SpringLiquibase;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class DatabaseTestConfiguration {
//    private static HikariDataSource buildHikariDataSource() {
//        return (HikariDataSource) DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean("goalTodoListDataSource")
//    @Primary
//    @ConfigurationProperties("datasource.goal_todo_list")
//    public HikariDataSource tasksExtensionsDataSource() {
//        HikariDataSource hikariDataSource = buildHikariDataSource();
//        hikariDataSource.setMaximumPoolSize(2);
//        return hikariDataSource;
//    }
//
//    @Bean
//    public SpringLiquibase liquibase(@Qualifier("goalTodoListDataSource") HikariDataSource dataSource,
//                                     LiquibaseProperties liquibaseProperties) {
//
//        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
//        liquibase.setContexts(liquibaseProperties.getContexts());
//        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
//        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
//        liquibase.setShouldRun(true);
//        return liquibase;
//    }
//}
