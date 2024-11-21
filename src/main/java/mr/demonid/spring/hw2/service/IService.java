package mr.demonid.spring.hw2.service;

import mr.demonid.spring.hw2.model.User;

import java.util.List;

/**
 * Интерфейс-прокладка для взаимодействия между контроллером и services
 */
public interface IService {

    List<User> findAll();
    User findById(int id);
    User saveUser(User user);
    void deleteById(int id);
    User update(User user);
}
