package com.omcbappeda.sumsel.utilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {
	private static Session session;
	
	public static final Session getSession(){
		if (session == null) {
			session = getSessionFactory().openSession();
			session.beginTransaction();
		}
		
		return session;
	}
	
	public static final void commitSession() {
		session.getTransaction().commit();
		if(session!=null){
			session.close();
		}
		session = null;
	}
	
	private static SessionFactory getSessionFactory(){
		return new Configuration().configure().buildSessionFactory();
	}
}
