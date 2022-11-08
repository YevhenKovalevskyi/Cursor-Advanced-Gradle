package hw04.task1.repositories;

import hw04.task1.entities.User;
import hw04.task1.messages.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@Repository
public class UserRepository {
    
    private static final String TABLE = "User";
    private static final String PREFIX = "u";
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void insert(User user) {
        entityManager.persist(user);
        System.out.println(Messages.USER_INSERTED.getOutMessage());
    }
    
    @Transactional
    public void update(User user) {
        user.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        entityManager.merge(user);
        System.out.println(Messages.USER_UPDATED.getOutMessage());
    }
    
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
        System.out.println(Messages.USER_DELETED.getOutMessage());
    }
    
    @Transactional
    public void delete(Integer id) {
        Optional<User> user = findOne(id);
        
        if (user.isPresent()) {
            entityManager.remove(user);
            System.out.println(Messages.USER_DELETED.getOutMessage());
        } else {
            System.out.println(Messages.USER_NOT_DELETED.getOutMessage());
        }
    }
    
    public List<User> findAll() {
        return entityManager.createQuery("FROM " + TABLE, User.class).getResultList();
    }
    
    public Optional<User> findOne(Integer id) {
        User user = entityManager.find(User.class, id);
        
        return user != null ? Optional.of(user) : Optional.empty();
    }
}
