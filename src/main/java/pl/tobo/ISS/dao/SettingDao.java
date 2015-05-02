/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.tobo.ISS.entities.GlobalSetting;

public class SettingDao {
	private EntityManager em;

	public SettingDao(EntityManager em) {
		this.em = em;
	}

	public GlobalSetting getGlobalSettingByKey(String key) {
		GlobalSetting gs = em.find(GlobalSetting.class, key);
		if (gs == null) {
			System.out.println("ERROR: GlobalSetting with key: " + key
					+ " not found in DB.");
		}
		return gs;
	}

	public List<GlobalSetting> getAllGlobalSettings() {
		List<GlobalSetting> settings = (List<GlobalSetting>) em.createQuery(
				"SELECT c FROM GlobalSetting c", GlobalSetting.class)
				.getResultList();
		return settings;
	}

	public boolean addSetting(GlobalSetting setting, boolean force) {
		
		// exists and 
		if (getGlobalSettingByKey(setting.getKey()) != null )
			// ... force 
			if(force) {
				if (!removeSetting(setting)) {
					System.out.println("Cannot remove setting");
					return false;
				}
				// ... not forced
			} else {
				System.out.println("Setting: " + setting.getKey()
						+ " already exists. And not forced to set it again.");
				return false;
		}
		// does not exists
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(setting);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}

	public boolean removeSetting(GlobalSetting setting) {
		GlobalSetting gs = getGlobalSettingByKey(setting.getKey());
		if (gs != null) {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				em.remove(gs);
				em.flush();
				et.commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				et.rollback();
				return false;
			}
		} else {
			System.out.println("Cannot remove not existing setting.");
			return false;
		}
	}

}
