package de.cmis.test.General;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;


/**
 * Abstrakte Oberklasse für Atompub-Verbindungen.
 * 
 * @author Krishna / Diederichs
 */
public abstract class RepositoryUtilBrowser {
	private String userName;
	private String password;
	private String urlToConnect;

	protected Map<String, String> parameters = new HashMap<>();

	public RepositoryUtilBrowser(String userName, String password, String urlToConnect) {
		this.userName = userName;
		this.password = password;
		this.urlToConnect = urlToConnect;
	}

	public void populateProperties() {
		// user credentials
		parameters.put(SessionParameter.USER, this.getUserName());
		parameters.put(SessionParameter.PASSWORD, this.getPassword());
	}

	/**
	 * Gibt alle Repositories für gegebenen Endpoint zurück.
	 * 
	 * @param parameters
	 * @return
	 */
	public List<Repository> getAllRepositories() {
		populateProperties();
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUrlToConnect() {
		return urlToConnect;
	}

}