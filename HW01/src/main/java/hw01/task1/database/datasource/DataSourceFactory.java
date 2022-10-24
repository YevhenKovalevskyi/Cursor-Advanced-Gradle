package hw01.task1.database.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.pool.OracleDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DataSourceFactory {
    
    private static final String DB_PROPS = "jdbc.properties";
    
    public static DataSource getDataSource(String db) {
        DataSource ds = null;
        
        switch (db) {
            case "mysql" -> ds = getMySQLDataSource();
            case "pgsql" -> ds = getPgSQLDataSource();
            case "oracle" -> ds = getOracleDataSource();
        }
        
        return ds;
    }
    
    /**
     * Get DataSource for MySQL
     * 1. Got path to jdbc.properties
     * 2. Load jdbc.properties
     * 3. Create DataSource
     */
    public static DataSource getMySQLDataSource() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        MysqlDataSource ds = null;
        
        try (InputStream is = loader.getResourceAsStream(DB_PROPS)) {
            props.load(is);
            
            ds = new MysqlDataSource();
            ds.setURL(props.getProperty("jdbc.mysql.url"));
            ds.setUser(props.getProperty("jdbc.mysql.user"));
            ds.setPassword(props.getProperty("jdbc.mysql.password"));
            ds.setServerTimezone("UTC");
        } catch (IOException e) {
            log.error("jdbc.properties file not found / loaded error.");
            e.printStackTrace();
        } catch (SQLException e) {
            log.error("MySQL DataSource setServerTimezone error.");
            e.printStackTrace();
        }
        
        return ds;
    }
    
    /**
     * Get DataSource for PgSQL
     * 1. Got path to jdbc.properties
     * 2. Load jdbc.properties
     * 3. Create DataSource
     */
    public static DataSource getPgSQLDataSource() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        PGSimpleDataSource ds = null;
    
        try (InputStream is = loader.getResourceAsStream(DB_PROPS)) {
            props.load(is);
            
            ds = new PGSimpleDataSource();
            ds.setUrl(props.getProperty("jdbc.pgsql.url"));
            ds.setUser(props.getProperty("jdbc.pgsql.user"));
            ds.setPassword(props.getProperty("jdbc.pgsql.password"));
        } catch (IOException e) {
            log.error("jdbc.properties file not found / loaded error.");
            e.printStackTrace();
        }
        
        return ds;
    }
    
    /**
     * Get DataSource for Oracle
     * 1. Got path to jdbc.properties
     * 2. Load jdbc.properties
     * 3. Create DataSource
     */
    public static DataSource getOracleDataSource() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        OracleDataSource ds = null;
        
        try (InputStream is = loader.getResourceAsStream(DB_PROPS)) {
            props.load(is);
            
            ds = new OracleDataSource();
            ds.setDriverType(props.getProperty("jdbc.oracle.type"));
            ds.setServerName(props.getProperty("jdbc.oracle.host"));
            ds.setPortNumber(Integer.parseInt(props.getProperty("jdbc.oracle.port")));
            ds.setDatabaseName(props.getProperty("jdbc.oracle.dbname"));
            ds.setUser(props.getProperty("jdbc.oracle.user"));
            ds.setPassword(props.getProperty("jdbc.oracle.password"));
        } catch (IOException e) {
            log.error("jdbc.properties file not found / loaded error.");
            e.printStackTrace();
        } catch (SQLException e) {
            log.error("DataSource connection failed.");
            throw new RuntimeException(e);
        }
    
        return ds;
    }
}
