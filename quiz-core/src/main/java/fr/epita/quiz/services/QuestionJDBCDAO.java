package fr.epita.quiz.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionJDBCDAO {

    public QuestionJDBCDAO() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test");
        System.out.println(connection.getSchema());
        connection.close();

    }
}
