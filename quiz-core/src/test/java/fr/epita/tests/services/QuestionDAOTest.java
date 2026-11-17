package fr.epita.tests.services;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.ServiceProvider;
import fr.epita.quiz.services.api.IQuestionDAO;
import fr.epita.quiz.services.impl.QuestionJDBCDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class QuestionDAOTest {


    @Test
    public void testCreateNewQuestion() throws SQLException {

        IQuestionDAO questionDAO = ServiceProvider.getInstance().getQuestionDAO();

        IQuestionDAO questionDAO1 = ServiceProvider.getInstance().getService(IQuestionDAO.class);

        //IExamDAO dao = ServiceProvider.getInstance().getService(IExamDAO.class);


        questionDAO.create(new Question());


    }


}
