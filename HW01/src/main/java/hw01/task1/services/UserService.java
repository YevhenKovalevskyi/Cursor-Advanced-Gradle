package hw01.task1.services;

import hw01.task1.database.dao.impl.UserDAOImpl;
import hw01.task1.messages.Messages;
import lombok.extern.slf4j.Slf4j;
import hw01.task1.database.dao.UserDAO;
import hw01.task1.database.entities.User;

import java.util.List;

@Slf4j
public class UserService {
    
    private final UserDAO userDAO;
    
    public UserService(String db) {
        this.userDAO = new UserDAOImpl(db);
    }
    
    public User getUserById(Integer id) {
        log.info(Messages.GETTING_USER_BY_ID.getLogMessage());
        return userDAO.selectOne(id);
    }
    
    public User getUserByLoginAndPassword(String email, String password) {
        log.info(Messages.GETTING_USER_BY_CRED.getLogMessage());
        return userDAO.selectOne(email, password);
    }
    
    public List<User> getUsers() {
        log.info(Messages.GETTING_USERS_ALL.getLogMessage());
        return userDAO.selectAll();
    }
    
    public List<User> getUsersFilteredByAgeLess(int age, boolean isStrict) {
        log.info(Messages.GETTING_USERS_AGE_LESS.getLogMessage());
        return userDAO.selectAllByAgeLess(age, isStrict);
    }
    
    public int getUsersCountFilteredByAgeMore(int age, boolean isStrict) {
        log.info(Messages.GETTING_USERS_COUNT_ADULT.getLogMessage());
        return userDAO.selectCountByAgeMore(age, isStrict);
    }
    
    public List<User> getUsersFilteredByAgeBetween(int ageFrom, int ageTo) {
        log.info(Messages.GETTING_USERS_AGE_BETWEEN.getLogMessage());
        return userDAO.selectAllByAgeBetween(ageFrom, ageTo);
    }
    
    public List<User> getUsersFilteredByNameEnds(String str) {
        log.info(Messages.GETTING_USERS_NAME_ENDS.getLogMessage());
        return userDAO.selectAllByNameEnds(str);
    }
    
    public int getUsersCountFilteredByNameContains(String str) {
        log.info(Messages.GETTING_USERS_COUNT_NAME_CONT.getLogMessage());
        return userDAO.selectCountByNameContains(str);
    }
}
