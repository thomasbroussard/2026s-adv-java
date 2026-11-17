package fr.epita.quiz.services.api;

import fr.epita.quiz.datamodel.Question;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IQuestionDAO {
    Question create(Question question) throws SQLException;

    Optional<Question> findById(Long id) throws SQLException;

    List<Question> findAll() throws SQLException;

    boolean update(Question question) throws SQLException;

    boolean deleteById(Long id) throws SQLException;
}
