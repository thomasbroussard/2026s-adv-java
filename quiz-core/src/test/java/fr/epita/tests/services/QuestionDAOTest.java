package fr.epita.tests.services;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.IQuestionDAO;
import fr.epita.quiz.services.impl.QuestionJDBCDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class QuestionDAOTest {


    @Test
    public void testCreateNewQuestion() throws SQLException {
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";
        IQuestionDAO questionDAO = new QuestionJDBCDAO(url, user, password);


        questionDAO.create(new Question());


    }


}
