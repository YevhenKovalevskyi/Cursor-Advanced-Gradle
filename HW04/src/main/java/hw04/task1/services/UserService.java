package hw04.task1.services;

import hw04.task1.entities.User;
import hw04.task1.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public void createUser(User user) {
        userRepository.insert(user);
    }
    
    public void saveUser(User user) {
        userRepository.update(user);
    }
    
    public void removeUser(User user) {
        userRepository.delete(user);
    }
    
    public List<User> selectAll() {
        return userRepository.findAll();
    }
    
    public Optional<User> selectOne(Integer id) {
        return userRepository.findOne(id);
    }
}
