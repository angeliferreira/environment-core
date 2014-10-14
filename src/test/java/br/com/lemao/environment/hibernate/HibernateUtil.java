package br.com.lemao.environment.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	static SessionFactory sf;
	
	public static Session getCurrentSession() {
		loadSessionFactory();
		return sf.getCurrentSession();
	}
	
	private static void loadSessionFactory() {
		if (sf == null) {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sf = configuration.buildSessionFactory(builder.build());
		}
	}
	
}
