/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBConfig implements ServletContextListener{
	private static EntityManagerFactory emf;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		emf = Persistence.createEntityManagerFactory(StringConstants.PERSISTENCE_UNIT);

	}

	public static EntityManager getEntityManager() {
		if (emf != null)
			return emf.createEntityManager();
		else
			return null;
	}

}
