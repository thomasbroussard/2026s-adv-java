package fr.epita.quiz.services;
import fr.epita.quiz.datamodel.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionJDBCDAO {

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    public QuestionJDBCDAO() throws SQLException {
        initDatabase();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    /**
     * Initializes the table without the body column.
     */
    private void initDatabase() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS questions (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(255) NOT NULL
            );
        """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    /**
     * Inserts a new Question with title only.
     */
    public Question create(Question question) throws SQLException {
        String sql = "INSERT INTO questions (title) VALUES (?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, question.getTitle());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getLong(1));
                }
            }
        }
        return question;
    }

    /**
     * Finds a Question by primary key.
     */
    public Optional<Question> findById(Long id) throws SQLException {
        String sql = "SELECT id, title FROM questions WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToQuestion(rs));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Retrieves all questions.
     */
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT id, title FROM questions";
        List<Question> questions = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                questions.add(mapRowToQuestion(rs));
            }
        }
        return questions;
    }

    /**
     * Updates an existing Question's title.
     */
    public boolean update(Question question) throws SQLException {
        String sql = "UPDATE questions SET title = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, question.getTitle());
            stmt.setLong(2, question.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Deletes a Question by primary key.
     */
    public boolean deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM questions WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Question mapRowToQuestion(ResultSet rs) throws SQLException {
        return new Question(
                rs.getLong("id"),
                rs.getString("title")
        );
    }
}