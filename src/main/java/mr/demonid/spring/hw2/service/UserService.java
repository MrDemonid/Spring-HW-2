package mr.demonid.spring.hw2.service;

import mr.demonid.spring.hw2.model.User;
import mr.demonid.spring.hw2.repositories.IRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IService {

    private final IRepository userRepository;

    public UserService(IRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

}

