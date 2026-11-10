package fr.epita.tests.services;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.QuestionJDBCDAO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;

public class QuestionJDBCDAOTest {


    @Test
    @DisplayName("AT001-When Creating a new Question From the DAO, it should persist in db")
    public void testCreateNewQuestion() throws SQLException {
       //given
        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();
        Question question = new Question();
        question.setTitle("Depuis quand Paris s'appelle Paris ?");
        question.setId(1L);

        //when
       questionJDBCDAO.create(question);

       //then
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        ResultSet rs = connection.prepareStatement("SELECT * FROM questions where id = 1").executeQuery();
        int size = 0;
        while(rs.next()) {
            Assertions.assertEquals(question.getTitle(), rs.getString("title"));
            size++;
        }
        Assertions.assertEquals(1, size);
        connection.close();

    }

    @ParameterizedTest
    @CsvSource({
            "question1, 1",
            "question2, 2",
            "question3, 3"
    }
    )
    @DisplayName("AT001b-When Creating a new Question From the DAO, it should persist in db")
    public void testCreateNewQuestionParameterized(String questionTitle, Long questionId) throws SQLException {
        //given

        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();
        Question question = new Question();
        question.setTitle(questionTitle);
        question.setId(questionId);

        //when
        questionJDBCDAO.create(question);

        //then
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions where id = ?");
        preparedStatement.setLong(1, questionId);
        ResultSet rs = preparedStatement.executeQuery();
        int size = 0;
        while(rs.next()) {
            Assertions.assertEquals(question.getTitle(), rs.getString("title"));
            size++;
        }
        Assertions.assertEquals(1, size);

        connection.close();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        System.out.println("After each");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        connection.prepareStatement("DELETE FROM questions").execute();
        connection.close();
    }

    @BeforeAll
    public static void setUpAll() throws SQLException {
        System.out.println("Before all");
    }

    @AfterAll
    public static void tearDownAll() throws SQLException {
        System.out.println("After all");
    }

    @Test
    @DisplayName("AT002-When Creating a new Question From the DAO, it should persist in db")
    public void testUpdateQuestion() throws SQLException {
        //given

        QuestionJDBCDAO questionJDBCDAO = new QuestionJDBCDAO();
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        connection.prepareStatement("INSERT INTO questions (id, title) values (1, 'testquestion?')").execute();
        connection.close();

        //when
        Question question = new Question();
        question.setTitle("Depuis quand Paris s'appelle Paris ?");
        question.setId(1L);
        questionJDBCDAO.update(question);

        //then
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        ResultSet rs = connection.prepareStatement("SELECT * FROM questions where id = 1").executeQuery();
        int size = 0;
        while(rs.next()) {
            Assertions.assertEquals(question.getTitle(), rs.getString("title"));
            size++;
        }
        Assertions.assertEquals(1, size);
        connection.close();

    }



}
