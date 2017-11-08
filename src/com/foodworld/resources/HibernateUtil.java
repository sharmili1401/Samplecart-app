package com.foodworld.resources;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
	
}
