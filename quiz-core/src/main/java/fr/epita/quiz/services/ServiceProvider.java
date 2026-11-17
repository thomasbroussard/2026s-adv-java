package fr.epita.quiz.services;

import fr.epita.quiz.services.api.IQuestionDAO;
import fr.epita.quiz.services.impl.QuestionJDBCDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ServiceProvider {

    private Map<Class, Object> servicesRegistry = new HashMap<>();

    private Map<Class, Supplier<?>> serviceSuppliers = new HashMap<>();

    private static ServiceProvider instance;

    private ServiceProvider() {
        serviceSuppliers.put(IQuestionDAO.class, this::getQuestionDAO);
        serviceSuppliers.put(IExamDAO.class, this::getExamDAO);
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }



    public IQuestionDAO getQuestionDAO() {
        //TODO load from properties
        if (!servicesRegistry.containsKey(IQuestionDAO.class)) {
            try {
                QuestionJDBCDAO dao = new QuestionJDBCDAO("jdbc:h2:mem:test", "sa", "");
                servicesRegistry.put(IQuestionDAO.class, dao);
                return dao;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return (IQuestionDAO) servicesRegistry.get(IQuestionDAO.class);
    }

    public <T>  T getService(Class<T> clazz){
        T serviceInstance = (T) servicesRegistry.get(clazz);
        if (serviceInstance == null) {
            serviceInstance = (T) serviceSuppliers.get(clazz).get();
            servicesRegistry.put(clazz, serviceInstance);
        }
        return serviceInstance;
    }

}
