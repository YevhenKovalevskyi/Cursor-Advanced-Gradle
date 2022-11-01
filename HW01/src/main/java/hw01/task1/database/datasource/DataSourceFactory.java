package hw01.task1.database.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import hw01.task1.helpers.PropertiesHelper;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.pool.OracleDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DataSourceFactory {
    
    private static final Properties JDBC_PROPS = PropertiesHelper.getProperties("jdbc.properties");
    
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
     */
    public static DataSource getMySQLDataSource() {
        MysqlDataSource ds = null;
        
        if (!JDBC_PROPS.isEmpty()) {
            ds = new MysqlDataSource();
            ds.setURL(JDBC_PROPS.getProperty("jdbc.mysql.url"));
            ds.setUser(JDBC_PROPS.getProperty("jdbc.mysql.user"));
            ds.setPassword(JDBC_PROPS.getProperty("jdbc.mysql.password"));
        }
        
        return ds;
    }
    
    /**
     * Get DataSource for PgSQL
     */
    public static DataSource getPgSQLDataSource() {
        PGSimpleDataSource ds = null;
    
        if (!JDBC_PROPS.isEmpty()) {
            ds = new PGSimpleDataSource();
            ds.setUrl(JDBC_PROPS.getProperty("jdbc.pgsql.url"));
            ds.setUser(JDBC_PROPS.getProperty("jdbc.pgsql.user"));
            ds.setPassword(JDBC_PROPS.getProperty("jdbc.pgsql.password"));
        }
        
        return ds;
    }
    
    /**
     * Get DataSource for Oracle
     */
    public static DataSource getOracleDataSource() {
        OracleDataSource ds = null;
    
        if (!JDBC_PROPS.isEmpty()) {
            try {
                ds = new OracleDataSource();
                ds.setURL(JDBC_PROPS.getProperty("jdbc.oracle.url"));
                ds.setUser(JDBC_PROPS.getProperty("jdbc.oracle.user"));
                ds.setPassword(JDBC_PROPS.getProperty("jdbc.oracle.password"));
            } catch (SQLException e) {
                log.error("DataSource connection failed.");
                throw new RuntimeException(e);
            }
        }
    
        return ds;
    }
}
