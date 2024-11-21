package mr.demonid.spring.hw2.repositories;

import mr.demonid.spring.hw2.model.User;

import java.util.List;

/**
 * Интерфейс взаимодействия с БД
 */
public interface IRepository {

    List<User> findAll();
    User findById(int id);
    User save(User user);
    void deleteById(int id);
    User update(User user);
}
