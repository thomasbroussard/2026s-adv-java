package fr.epita.tests.services;

import fr.epita.quiz.services.api.IQuestionDAO;
import fr.epita.quiz.services.impl.QuestionJDBCDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

@Configuration
public class TestConfiguration {


    @Bean
    public IQuestionDAO questionJDBCDAO(DataSource ds) throws SQLException {
        return new QuestionJDBCDAO(ds);
    }

    @Bean(name = "testString")
    public String dummyQuestionTitle() {
        return "testquestion?" ;
    }

    @Bean(name = "testString2")
    public String dummyQuestionTitle2() {
        return "testquestion?" ;
    }

    @Bean
    public DataSource mainDataSource() {
        return new DriverManagerDataSource("jdbc:h2:mem:test", "sa", "");
    }
}
