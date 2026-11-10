package fr.epita.tests.services;

import fr.epita.quiz.services.QuestionJDBCDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class QuestionJDBCDAOTest {

    @Test
    @DisplayName("AT001-When Creating a new Question From the DAO, it should persist in db")
    public void testCreateNewQuestion() throws SQLException {
       //given

       //when
        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();

       //then
        //?

    }
}
