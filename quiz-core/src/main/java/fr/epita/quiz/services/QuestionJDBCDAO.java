package fr.epita.quiz.services;

import java.sql.Connection;
import java.sql.DriverManager;

public class QuestionJDBCDAO {

    public QuestionJDBCDAO() {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test");
    }
}
