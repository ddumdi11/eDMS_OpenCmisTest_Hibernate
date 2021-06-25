package de.cmis.test.General;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Tool;

/**
 * Erstellt und gibt eine Session über einen Repository-Endpoint zurück.
 * Test wird für alle gefundenen Repositories ausgeführt.
 * Beim OpenCmisInmemory-Server sind dafür keine Benutzerangaben nötig.
 * 
 * @author Krishna / Diederichs
 *
 */

public class GetSessionFromRepositoryEndpoint {

	/**
	 * Listet alle Repositories zum gegebenen EntryPoint des Servers auf.
	 * 
	 * @param serverURL
	 * @return
	 */
	public static List<Repository> getRepositories(String serverURL) {
		Map<String, String> parameters = new HashMap<>();		

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		if (serverURL.contains("atom")) {
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			parameters.put(SessionParameter.ATOMPUB_URL, serverURL);
		} else if (serverURL.contains("browser")) {
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
			parameters.put(SessionParameter.BROWSER_URL, serverURL);
		}		

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public static void go() {
		Tool.printAndLog("Atompub (CMIS 1.0");
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom";
		List<Repository> repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Tool.printAndLog(repository.getId() + " " + session.getRepositoryInfo().getName());
		}
		
		Tool.printAndLog("Atompub (CMIS 1.1");
		serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Tool.printAndLog(repository.getId() + " " + session.getRepositoryInfo().getName());
		}
		
		Tool.printAndLog("Browser (CMIS 1.1");
		serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
		repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Tool.printAndLog(repository.getId() + " " + session.getRepositoryInfo().getName());
		}
	}
}