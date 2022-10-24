package hw01.task1.database.dao;

import hw01.task1.database.entities.User;

import java.util.List;

public interface UserDAO extends BaseDAO<Integer, User> {
    
    User selectOne(String email, String password);
    List<User> selectAllByAgeLess(int age, boolean isStrict);
    List<User> selectAllByAgeBetween(int ageFrom, int ageTo);
    List<User> selectAllByNameEnds(String str);
    int selectCountByNameContains(String str);
    int selectCountByAgeMore(int age, boolean isStrict);
}
