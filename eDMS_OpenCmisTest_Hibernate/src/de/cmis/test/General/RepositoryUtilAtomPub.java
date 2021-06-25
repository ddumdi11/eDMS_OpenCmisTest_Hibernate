package de.cmis.test.General;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;

/**
 * Abstrakte Oberklasse für Atompub-Verbindungen.
 * 
 *
 */
public abstract class RepositoryUtilAtomPub {
	private String userName;
	private String password;
	private String urlToConnect;

	protected Map<String, String> parameters = new HashMap<>();

	public RepositoryUtilAtomPub(String userName, String password, String urlToConnect) {
		this.userName = userName;
		this.password = password;
		this.urlToConnect = urlToConnect;
	}

	public abstract void populateProperties();

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