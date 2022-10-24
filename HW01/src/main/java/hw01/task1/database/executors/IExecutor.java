package hw01.task1.database.executors;

import java.util.List;
import java.util.Map;

public interface IExecutor {
    
    int write(String query);
    int writeWithParams(String query, Map<Object, Integer> params);
    List<Map<String, String>> read(String query);
    List<Map<String, String>> readWithParams(String query, Map<Object, Integer> params);
}
