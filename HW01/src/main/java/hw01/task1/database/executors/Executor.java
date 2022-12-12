package hw01.task1.database.executors;

import hw01.task1.database.datasource.DataSourceFactory;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;

import java.util.*;

@Slf4j
public class Executor implements IExecutor {
    
    DataSource dataSource;
    
    public Executor(String db) {
        this.dataSource = DataSourceFactory.getDataSource(db);
    }
    
    /**
     * Execute Insert/Update/Delete query
     * 1. Create Connection and Statement
     * 2. Execute query
     * 3. Rollback if Exception
     */
    @Override
    public int write(String query) {
        int result = 0;
    
        try {
            @Cleanup Connection connection = dataSource.getConnection();
            @Cleanup Statement stmt = connection.createStatement();
            
            try {
                connection.setAutoCommit(false);
                result = stmt.executeUpdate(query);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("SQL query execution error. Transaction rolled back.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            log.error("SQL query execution error. Wrapper error.");
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Execute parametrized Insert/Update/Delete query
     * 1. Create Connection and PreparedStatement
     * 2. Execute query
     * 3. Rollback if Exception
     */
    @Override
    public int writeWithParams(String query, Map<Object, Integer> params) {
        int result = 0;
        
        try {
            @Cleanup Connection connection = dataSource.getConnection();
            @Cleanup PreparedStatement stmt = connection.prepareStatement(query);
            
            try {
                connection.setAutoCommit(false);
    
                int parameterIndex = 1;
                
                for (Map.Entry<Object, Integer> param : params.entrySet()) {
                    stmt.setObject(parameterIndex++, param.getKey(), param.getValue());
                }
                
                result = stmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("SQL query execution error. Transaction rolled back.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            log.error("SQL query execution error. Wrapper error.");
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Execute Select query
     * 1. Create Connection, Statement and ResultSet
     * 2. Execute query
     * 3. Receive ResultSet metaData.
     * 4. Fill the result ArrayList with the HashMaps of the query data.
     */
    @Override
    public List<Map<String, String>> read(String query) {
        List<Map<String, String>> result = new ArrayList<>();
        
        try {
            @Cleanup Connection connection = dataSource.getConnection();
            @Cleanup Statement stmt = connection.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);
            
            ResultSetMetaData md = rs.getMetaData();
            
            int columnCount = md.getColumnCount();
            
            while (rs.next()) {
                TreeMap<String, String> items = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                
                for (int i = 1; i <= columnCount; i++) {
                    items.put(md.getColumnLabel(i), rs.getString(i));
                }

                result.add(items);
            }
        } catch (SQLException e) {
            log.error("SQL query execution error. Wrapper error.");
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Execute parametrized Select query
     * 1. Create Connection and PreparedStatement
     * 2. Execute query
     * 3. Create ResultSet and Receive metaData
     * 4. Fill the result ArrayList with the HashMaps of the query data
     */
    @Override
    public List<Map<String, String>> readWithParams(String query, Map<Object, Integer> params) {
        List<Map<String, String>> result = new ArrayList<>();

        try {
            @Cleanup Connection connection = dataSource.getConnection();
            @Cleanup PreparedStatement stmt = connection.prepareStatement(query);
    
            int parameterIndex = 1;
    
            for (Map.Entry<Object, Integer> param : params.entrySet()) {
                stmt.setObject(parameterIndex++, param.getKey(), param.getValue());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                
                int columnCount = md.getColumnCount();
                
                while (rs.next()) {
                    Map<String, String> items = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                    
                    for (int i = 1; i <= columnCount; i++) {
                        items.put(md.getColumnLabel(i), rs.getString(i));
                    }
                    
                    result.add(items);
                }
            }
        } catch (SQLException e) {
            log.error("SQL query execution error. Wrapper error.");
            e.printStackTrace();
        }
        
        return result;
    }
}
