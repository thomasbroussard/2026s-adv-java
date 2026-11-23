package fr.epita.tests.services;

import fr.epita.quiz.services.api.IQuestionDAO;
import fr.epita.quiz.services.impl.QuestionJDBCDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class TestConfiguration {


    @Bean
    public IQuestionDAO questionJDBCDAO() throws SQLException {
        return new QuestionJDBCDAO("jdbc:h2:mem:test", "sa", "");

    }

    @Bean(name = "testString")
    public String dummyQuestionTitle() {
        return "testquestion?" ;
    }

    @Bean(name = "testString2")
    public String dummyQuestionTitle2() {
        return "testquestion?" ;
    }
}
