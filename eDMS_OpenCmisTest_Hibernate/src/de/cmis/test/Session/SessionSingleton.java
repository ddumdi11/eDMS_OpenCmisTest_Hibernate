package de.cmis.test.Session;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;

public class SessionSingleton {

	private static SessionSingleton instance; // vor Zugriff von außen geschützt und statisch

	private SessionSingleton() {
	} // privater Konstruktor mit Zugriffsschutz von außen

	public static SessionSingleton getInstance() { // öffentliche Methode, Aufruf durch Code
		if (instance == null) { // nur wenn keine Instanz besteht, dann erstelle eine neue
			instance = new SessionSingleton();
		}
		return instance;
	}

	public Session getSession(TestSetting setting) {
		
		String bindingType = setting.getBindingType();
		String bindingUrl = setting.getBindingUrl();
		String userName = setting.getUserName();
		String userPwd = setting.getUserPwd();

		Tool.printAndLog("Binding: " + bindingUrl);

		Session session = null;

		// No connection to OpenCmisServer available, create a new one
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.USER, userName);
		parameters.put(SessionParameter.PASSWORD, userPwd);
		if (bindingType.contains("atom")) {
			parameters.put(SessionParameter.ATOMPUB_URL, bindingUrl);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		} else if (bindingType.contains("browser")) {
			parameters.put(SessionParameter.BROWSER_URL, bindingUrl);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
		}
		parameters.put(SessionParameter.COMPRESSION, "true");
		parameters.put(SessionParameter.CACHE_TTL_OBJECTS, "0");

		// If there is only one repository exposed (e.g. Alfresco),
		// these lines will help detect it and its ID
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		Repository defaultRepository = null;
		if (repositories != null && repositories.size() > 0) {
			Tool.printAndLog("Found (" + repositories.size() + ") repositories");
			defaultRepository = repositories.get(0);
			Tool.printAndLog("Info about the first OpenCmisServer repo [ID=" + defaultRepository.getId() + "][name="
					+ defaultRepository.getName() + "][CMIS ver supported="
					+ defaultRepository.getCmisVersionSupported() + "]");
		} else {
			throw new CmisConnectionException("Could not connect to the OpenCmisServer, " + "no repository found!");
		}

		// Create a new session with the Alfresco repository
		session = defaultRepository.createSession();

		// Save connection for reuse
		// connections.put(connectionName, session);

		// Rückmeldung, dass die Session erzeugt wurde
		Tool.printAndLog("Die Session wurde erzeugt!");

		// Rückgabe
		return session;
	}

	public Session getSession(TestSetting setting, String repositoryId) {
		
		String bindingType = setting.getBindingType();
		String bindingUrl = setting.getBindingUrl();
		String userName = setting.getUserName();
		String userPwd = setting.getUserPwd();
		
		Tool.printAndLog("Binding: " + bindingUrl);
		
		Session session = null;

		// No connection to OpenCmisServer available, create a new one
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.USER, userName);
		parameters.put(SessionParameter.PASSWORD, userPwd);
		if (bindingType.contains("atom")) {
			parameters.put(SessionParameter.ATOMPUB_URL, bindingUrl + bindingType);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		} else if (bindingType.contains("browser")) {
			parameters.put(SessionParameter.BROWSER_URL, bindingUrl + bindingType);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
		}
		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);

		session = sessionFactory.createSession(parameters);

		// Rückmeldung, dass die Session erzeugt wurde
		Tool.printAndLog("Die Session wurde erzeugt!");

		// Rückgabe
		return session;
	}

}
