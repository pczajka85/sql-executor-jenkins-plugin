package hudson.plugins.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static String username;
	private static String dialect;
	private static String driverClass;
	private static String url;
	private static String password;
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory createSessionFactory() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.username", username);
		prop.setProperty("hibernate.dialect", dialect);
		prop.setProperty("hibernate.connection.driver_class", driverClass);
		prop.setProperty("hibernate.connection.url", url);
		prop.setProperty("hibernate.connection.password", password);
		prop.setProperty("hibernate.hbm2ddl.auto", "validate");
		prop.setProperty("show_sql", "true");
				
		try {
			return new Configuration().setProperties(prop).buildSessionFactory();
			
		} catch (Throwable ex) {
			System.err.println("Database connection error :) "+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null){
			sessionFactory = createSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void setUsername(String username) {
		HibernateUtil.username = username;
	}

	public static void setDialect(String dialect) {
		HibernateUtil.dialect = dialect;
	}

	public static void setDriverClass(String driverClass) {
		HibernateUtil.driverClass = driverClass;
	}

	public static void setUrl(String url) {
		HibernateUtil.url = url;
	}

	public static void setPassword(String password) {
		HibernateUtil.password = password;
	}
}
