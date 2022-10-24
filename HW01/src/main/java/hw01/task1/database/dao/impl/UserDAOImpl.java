package hw01.task1.database.dao.impl;

import hw01.task1.database.executors.Executor;
import hw01.task1.database.executors.IExecutor;
import lombok.extern.slf4j.Slf4j;
import hw01.task1.database.dao.UserDAO;
import hw01.task1.database.entities.User;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserDAOImpl implements UserDAO {
    
    private final IExecutor executor;
    
    public UserDAOImpl(String db) {
        this.executor = new Executor(db);
    }
    
    /**
     * Insert new User
     */
    @Override
    public void insert(User user) {
        String query = " INSERT INTO users SET u_email = ?, u_first_name = ?, u_last_name = ?, u_age = ?, u_gender = ? ";
        
        Map<Object, Integer> params = new HashMap<>();
        params.put(user.getEmail(), Types.VARCHAR);
        params.put(user.getFirstName(), Types.VARCHAR);
        params.put(user.getLastName(), Types.VARCHAR);
        params.put(user.getAge(), Types.SMALLINT);
        params.put(user.getGender(), Types.VARCHAR);
    
        executor.writeWithParams(query, params);
    }
    
    /**
     * Update certain User
     */
    @Override
    public int update(User user) {
        String query =
                " UPDATE users " +
                " SET u_email = ?, u_first_name = ?, u_last_name = ?, u_age = ?, u_gender = ?, updated_at = NOW() " +
                " WHERE u_id = ? ";
        
        Map<Object, Integer> params = new HashMap<>();
        params.put(user.getEmail(), Types.VARCHAR);
        params.put(user.getFirstName(), Types.VARCHAR);
        params.put(user.getLastName(), Types.VARCHAR);
        params.put(user.getAge(), Types.SMALLINT);
        params.put(user.getGender(), Types.VARCHAR);
    
        return executor.writeWithParams(query, params);
    }
    
    /**
     * Delete certain User
     */
    @Override
    public void delete(User user) {
        String query = " DELETE FROM users WHERE u_id = ? ";
        
        Map<Object, Integer> params = new HashMap<>();
        params.put(user.getId(), Types.SMALLINT);
    
        executor.writeWithParams(query, params);
    }
    
    /**
     * Delete User by ID
     */
    @Override
    public void delete(Integer id) {
        String query = " DELETE FROM users WHERE u_id = ? ";
        
        Map<Object, Integer> params = new HashMap<>();
        params.put(id, Types.SMALLINT);
        
        executor.writeWithParams(query, params);
    }
    
    /**
     * Select User by ID
     */
    @Override
    public User selectOne(Integer id) {
        User user = null;
    
        String query =
                " SELECT u_id id, u_email email, u_first_name firstName, u_last_name lastName, u_age age, u_gender gender " +
                " FROM users WHERE u_id = ? ";
        Map<Object, Integer> params = new HashMap<>();
        params.put(id, Types.SMALLINT);
    
        List<Map<String, String>> result = executor.readWithParams(query, params);
    
        if (result.size() > 0) {
            Map<String, String> item = result.get(0);
        
            user = new User();
            user.setId(Integer.parseInt(item.get("id")));
            user.setEmail(item.get("email"));
            user.setFirstName(item.get("firstName"));
            user.setLastName(item.get("lastName"));
            user.setAge(Integer.parseInt(item.get("age")));
            user.setGender(item.get("gender"));
        }
        
        return user;
    }
    
    /**
     * Select User by credentials
     */
    @Override
    public User selectOne(String email, String password) {
        User user = null;
        
        String query =
                " SELECT u_id id, u_email email, u_first_name firstName, u_last_name lastName, u_age age, u_gender gender " +
                " FROM users WHERE u_email = ? AND u_password = ? "; /* handle password(?) */
        Map<Object, Integer> params = new HashMap<>();
        params.put(email, Types.VARCHAR);
        params.put(password, Types.VARCHAR);

        List<Map<String, String>> result = executor.readWithParams(query, params);

        if (result.size() > 0) {
            Map<String, String> item = result.get(0);
    
            user = new User();
            user.setId(Integer.parseInt(item.get("id")));
            user.setEmail(item.get("email"));
            user.setFirstName(item.get("firstName"));
            user.setLastName(item.get("lastName"));
            user.setAge(Integer.parseInt(item.get("age")));
            user.setGender(item.get("gender"));
        }
        
        return user;
    }
    
    /**
     * Select all Users
     */
    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
    
        String query =
                " SELECT u_id id, u_email email, u_first_name firstName, u_last_name lastName, u_age age, u_gender gender " +
                " FROM users ORDER BY created_at DESC ";
    
        List<Map<String, String>> result = executor.read(query);
    
        if (result.size() > 0) {
            result.forEach(item -> {
                User user = new User();
                user.setId(Integer.parseInt(item.get("id")));
                user.setEmail(item.get("email"));
                user.setFirstName(item.get("firstName"));
                user.setLastName(item.get("lastName"));
                user.setAge(Integer.parseInt(item.get("age")));
                user.setGender(item.get("gender"));
                
                users.add(user);
            });
        }
        
        return users;
    }
    
    /**
     * Select all Users filtered by age less than a number
     */
    @Override
    public List<User> selectAllByAgeLess(int age, boolean isStrict) {
        List<User> users = new ArrayList<>();
    
        String sign = isStrict ? "<" : "<=";
        
        String query =
                " SELECT u_id id, u_email email, u_first_name firstName, u_last_name lastName, u_age age, u_gender gender " +
                " FROM users WHERE u_age " + sign + " ? ORDER BY created_at DESC ";
    
        Map<Object, Integer> params = new HashMap<>();
        params.put(age, Types.SMALLINT);

        List<Map<String, String>> result = executor.readWithParams(query, params);
        
        if (result.size() > 0) {
            result.forEach(item -> {
                User user = new User();
                user.setId(Integer.parseInt(item.get("id")));
                user.setEmail(item.get("email"));
                user.setFirstName(item.get("firstName"));
                user.setLastName(item.get("lastName"));
                user.setAge(Integer.parseInt(item.get("age")));
                user.setGender(item.get("gender"));
                
                users.add(user);
            });
        }
        
        return users;
    }
    
    /**
     * Select all Users filtered by age within a range
     */
    @Override
    public List<User> selectAllByAgeBetween(int ageFrom, int ageTo) {
        List<User> users = new ArrayList<>();
        
        String query =
                " SELECT u_id id, u_email email, u_first_name firstName, u_last_name lastName, u_age age, u_gender gender " +
                " FROM users WHERE u_age BETWEEN ? AND ? ORDER BY created_at DESC ";
    
        Map<Object, Integer> params = new HashMap<>();
        params.put(ageFrom, Types.SMALLINT);
        params.put(ageTo, Types.SMALLINT);
        
        List<Map<String, String>> result = executor.readWithParams(query, params);
        
        if (result.size() > 0) {
            result.forEach(item -> {
                User user = new User();
                user.setId(Integer.parseInt(item.get("id")));
                user.setEmail(item.get("email"));
                user.setFirstName(item.get("firstName"));
                user.setLastName(item.get("lastName"));
                user.setAge(Integer.parseInt(item.get("age")));
                user.setGender(item.get("gender"));
                
                users.add(user);
            });
        }
        
        return users;
    }
    
    /**
     * Select all Users filtered by name ends with str
     */
    @Override
    public List<User> selectAllByNameEnds(String str) {
        List<User> users = new ArrayList<>();
    
        str = "%" + str;
        
        String query =
                " SELECT u_id id, u_email email, u_first_name firstName, u_last_name lastName, u_age age, u_gender gender " +
                " FROM users WHERE u_first_name LIKE ? ORDER BY created_at DESC ";
    
        Map<Object, Integer> params = new HashMap<>();
        params.put(str, Types.VARCHAR);
        
        List<Map<String, String>> result = executor.readWithParams(query, params);
        
        if (result.size() > 0) {
            result.forEach(item -> {
                User user = new User();
                user.setId(Integer.parseInt(item.get("id")));
                user.setEmail(item.get("email"));
                user.setFirstName(item.get("firstName"));
                user.setLastName(item.get("lastName"));
                user.setAge(Integer.parseInt(item.get("age")));
                user.setGender(item.get("gender"));
                
                users.add(user);
            });
        }
        
        return users;
    }
    
    /**
     * Select count of all Users filtered by name ends contains str
     */
    @Override
    public int selectCountByNameContains(String str) {
        int count = 0;
    
        str = "%" + str + "%";
        
        String query = " SELECT count(u_id) count FROM users WHERE u_first_name LIKE ? ";
    
        Map<Object, Integer> params = new HashMap<>();
        params.put(str, Types.VARCHAR);
        
        List<Map<String, String>> result = executor.readWithParams(query, params);
        
        if (result.size() > 0) {
            count = Integer.parseInt(result.get(0).get("count"));
        }
        
        return count;
    }
    
    /**
     * Select count of all Users filtered by age more than a number
     */
    @Override
    public int selectCountByAgeMore(int age, boolean isStrict) {
        int count = 0;
    
        String sign = isStrict ? ">" : ">=";
        
        String query = " SELECT count(u_id) count FROM users WHERE u_age " + sign + " ? ";
    
        Map<Object, Integer> params = new HashMap<>();
        params.put(age, Types.SMALLINT);
        
        List<Map<String, String>> result = executor.readWithParams(query, params);
        
        if (result.size() > 0) {
            count = Integer.parseInt(result.get(0).get("count"));
        }
        
        return count;
    }
}
