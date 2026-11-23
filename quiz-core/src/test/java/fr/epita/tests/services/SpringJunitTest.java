package fr.epita.tests.services;


import fr.epita.quiz.services.api.IQuestionDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestConfiguration.class)
public class SpringJunitTest {

    @Autowired
    private IQuestionDAO questionDAO;

    @Autowired
    @Qualifier("testString")
    private String testString;

    @Test
    public void test() {
        Assertions.assertNotNull(questionDAO);
        System.out.println("Spring Junit Test");
        System.out.println(testString);
    }
}
