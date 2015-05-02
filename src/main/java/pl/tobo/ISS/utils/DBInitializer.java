/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import pl.tobo.ISS.dao.ContentDao;
import pl.tobo.ISS.dao.ScreenDao;
import pl.tobo.ISS.dao.SettingDao;
import pl.tobo.ISS.dao.TagDao;
import pl.tobo.ISS.dao.UserDao;
import pl.tobo.ISS.entities.GlobalSetting;

@WebListener
public class DBInitializer implements ServletRequestListener {
	
	Logger logger = Logger.getLogger("pl.tobo.ISS");
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		EntityManager em = DBConfig.getEntityManager();
		UserDao userDAO = new UserDao(em);
		TagDao tagDAO = new TagDao(em);
		ScreenDao screenDAO = new ScreenDao(em);
		ContentDao contentDAO = new ContentDao(em);
		SettingDao settingDAO = new SettingDao(em);

		ServletRequest req = arg0.getServletRequest();
		req.setAttribute(StringConstants.REQUEST_ATTR_USER_DAO, userDAO);
		req.setAttribute(StringConstants.REQUEST_ATTR_TAG_DAO, tagDAO);
		req.setAttribute(StringConstants.REQUEST_ATTR_SCREEN_DAO, screenDAO);
		req.setAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO, contentDAO);
		req.setAttribute(StringConstants.REQUEST_ATTR_SETTING_DAO, settingDAO);

		// TODO: move this into session (?) performance issues
		if(req.getAttribute("registrationDisabled") == null && settingDAO != null){
			GlobalSetting registrationDisabled = settingDAO.getGlobalSettingByKey(StringConstants.SETTING_REGISTRATION_DISABLED);
			if (registrationDisabled != null){
				
				logger.log(Level.INFO, "Registration disabled: "+registrationDisabled.getValue());
				if("TRUE".equals(registrationDisabled.getValue())){
					req.setAttribute("registrationDisabled", "TRUE");
				}
			}
		}

	}
	
}
