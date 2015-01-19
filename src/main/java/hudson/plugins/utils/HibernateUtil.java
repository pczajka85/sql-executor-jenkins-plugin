package hudson.plugins.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = createSessionFactory();
	
	public static SessionFactory createSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable ex) {
			System.err.println("Database connection error :) "+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
