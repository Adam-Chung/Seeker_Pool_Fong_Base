package common.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.zaxxer.hikari.HikariDataSource;

import common.util.JedisUtil;
import redis.clients.jedis.Jedis;


/* 解決錯誤 appears to have started a thread named [mysql-cj-abandoned-connection-cleanup] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 */


@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	
	private HikariDataSource dataSource;
	private Jedis jedis;	

	 @Override
	    public void contextDestroyed(ServletContextEvent sce) {
	        try {
	            // com.mysql.cj.jdbc.AbandonedConnectionCleanupThread Or com.mysql.jdbc.AbandonedConnectionCleanupThread
	            Class<?> cls = Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread");
	            Method method = cls.getMethod("checkedShutdown");
	            method.invoke(null);
	        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	            e.printStackTrace();
	        }
	        // Now deregister JDBC drivers in this context's ClassLoader:
	        // Get the webapp's ClassLoader
	        ClassLoader cl = Thread.currentThread().getContextClassLoader();
	        // Loop through all drivers
	        Enumeration<Driver> drivers = DriverManager.getDrivers();
	        while (drivers.hasMoreElements()) {
	            Driver driver = drivers.nextElement();
	            if (driver.getClass().getClassLoader() == cl) {
	                // This driver was registered by the webapp's ClassLoader, so deregister it:
	                try {
//	                	e.printStackTrace();
	                    DriverManager.deregisterDriver(driver);
	                } catch (SQLException ex) {
	                	ex.printStackTrace();
	                }
	            } else {
	                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
//	                log.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
	            }
	        }
	        //關閉dataSource
	        if(dataSource != null) {
	        	dataSource.close(); 
	        }     
	        JedisUtil.close(jedis);
	    }
}
