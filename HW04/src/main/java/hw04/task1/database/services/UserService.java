package hw04.task1.database.services;

import hw04.task1.database.entities.User;
import hw04.task1.database.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public void delete(User user) {
        userRepository.delete(user);
    }
    
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
    
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
