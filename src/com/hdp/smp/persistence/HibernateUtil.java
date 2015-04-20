package com.hdp.smp.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory ;
	static final String HB_ConfigFile = "hibernate.properties";
	
	public static SessionFactory builSessionFactory() {
		 // A SessionFactory is set up once for an application
		Properties prop = new Properties();
		try {
			InputStream in = HibernateUtil.class.getClassLoader().getResourceAsStream(HB_ConfigFile);
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    sessionFactory = new Configuration().addProperties(prop)
	    		.addAnnotatedClass(com.hdp.smp.model.Role.class)
	    		.addAnnotatedClass(com.hdp.smp.model.User.class)
	    		.addAnnotatedClass(com.hdp.smp.model.Station.class)
	    		.addAnnotatedClass(com.hdp.smp.model.Monitor.class)
	    		.addAnnotatedClass(com.hdp.smp.model.Shift.class)
	    		.addAnnotatedClass(com.hdp.smp.model.Spindle.class)
	    		.addAnnotatedClass(com.hdp.smp.model.SpindleData.class)
	    		.addAnnotatedClass(com.hdp.smp.model.StationData.class)
	    		.buildSessionFactory();
	    
	    return sessionFactory;
	}
	
	public static Session getSession(){
		builSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
