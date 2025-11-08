package com.wallet.app.config;

import com.wallet.app.exception.DatabaseConnectionException;
import com.wallet.app.exception.InvalidDatabaseConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Configuration class to validate database connectivity on startup
 * and provide connection health monitoring
 */
@Configuration
public class DatabaseConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    
    private final DataSource dataSource;
    
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    
    public DatabaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Check database connection when application starts
     * This will alert immediately if database is unreachable
     */
    @EventListener(ApplicationReadyEvent.class)
    public void validateDatabaseConnection() {
        logger.info("=".repeat(60));
        logger.info("VALIDATING DATABASE CONNECTION");
        logger.info("Database URL: {}", databaseUrl);
        logger.info("=".repeat(60));
        
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                logger.info("✅ Database connection SUCCESSFUL");
                logger.info("Connected to: {}", connection.getMetaData().getURL());
                logger.info("Database: {}", connection.getMetaData().getDatabaseProductName());
                logger.info("Version: {}", connection.getMetaData().getDatabaseProductVersion());
            } else {
                logger.error("❌ Database connection INVALID");
                throw new DatabaseConnectionException("Database connection validation failed");
            }
        } catch (SQLException ex) {
            logger.error("❌ DATABASE CONNECTION FAILED!");
            logger.error("Error: {}", ex.getMessage());
            
            String errorMessage = buildErrorMessage(ex);
            logger.error(errorMessage);
            
            // Don't throw exception on startup - let app start but log warning
            logger.warn("⚠️  Application started but database is UNREACHABLE!");
            logger.warn("⚠️  All database operations will fail until connection is restored");
        }
        
        logger.info("=".repeat(60));
    }
    
    /**
     * Build a detailed error message based on the SQL exception
     */
    private String buildErrorMessage(SQLException ex) {
        String message = ex.getMessage();
        
        if (message == null) {
            return "Unknown database error occurred";
        }
        
        if (message.contains("Communications link failure")) {
            return """
                
                ⚠️  COMMUNICATION FAILURE WITH DATABASE
                
                Possible causes:
                1. Database server is not running
                2. Wrong IP address in application.properties (currently: 10.108.5.222)
                3. Network connectivity issue (check WiFi)
                4. Firewall blocking connection
                5. Database port 3306 is not accessible
                
                Solutions:
                1. Verify your friend's laptop is on and MySQL is running
                2. Check if you're on the same WiFi network
                3. Confirm the IP address hasn't changed (use 'ipconfig' or 'ifconfig')
                4. Test connection: mysql -h 10.108.5.222 -u remote_user -p
                """;
        }
        
        if (message.contains("Connection refused")) {
            return """
                
                ⚠️  CONNECTION REFUSED BY DATABASE SERVER
                
                Possible causes:
                1. MySQL server is not running on 10.108.5.222
                2. MySQL is not configured to accept remote connections
                3. Wrong port number (default: 3306)
                
                Solutions:
                1. Ask your friend to start MySQL server
                2. Verify MySQL bind-address is set to 0.0.0.0 (not 127.0.0.1)
                3. Check MySQL is listening on port 3306
                """;
        }
        
        if (message.contains("Access denied")) {
            return """
                
                ⚠️  AUTHENTICATION FAILED
                
                Possible causes:
                1. Wrong username or password
                2. User 'remote_user' doesn't have remote access permissions
                
                Solutions:
                1. Verify credentials in application.properties
                2. Grant remote access: GRANT ALL ON digitalgoldwallet.* TO 'remote_user'@'%';
                """;
        }
        
        if (message.contains("Unknown database")) {
            return """
                
                ⚠️  DATABASE NOT FOUND
                
                The database 'digitalgoldwallet' doesn't exist on the server.
                
                Solution:
                Ask your friend to create the database:
                CREATE DATABASE digitalgoldwallet;
                """;
        }
        
        return "Database connection error: " + message;
    }
    
    /**
     * Check if database is currently reachable
     * Can be called by services before critical operations
     */
    public boolean isDatabaseReachable() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(3);
        } catch (SQLException ex) {
            logger.error("Database health check failed: {}", ex.getMessage());
            return false;
        }
    }
}
