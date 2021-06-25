package de.cmis.test.General;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Tool;

/**
 * Gibt eine Session zu einem spezifischen Repository zurück. Hier
 * voreingestellt Repository "A1" des OpenCmisInmemory-Servers.
 * 
 * @author Diederichs
 *
 */
public class GetSessionToRepository {

	/**
	 * Gibt eine Session zum Repository "A1" des OpenCmisInmemory-Servers zurück.
	 * Übergeben wird die Server-Url und es braucht kein Benutzer angegeben zu
	 * werden.
	 * 
	 * @param repositoryId
	 * @param serverURL
	 * @return
	 */
	public static Session getSession(String repositoryId, String serverURL) {
		Map<String, String> parameters = new HashMap<>();

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);

		if (serverURL.contains("atom")) {
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			parameters.put(SessionParameter.ATOMPUB_URL, serverURL);
		} else if (serverURL.contains("browser")) {
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
			parameters.put(SessionParameter.BROWSER_URL, serverURL);
		}

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

	public static void go() {
		Tool.printAndLog("Atompub (CMIS 1.0) EntryPoint");
		String repositoryId = "A1";
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom";
		Session session = getSession(repositoryId, serverURL);
		Tool.printAndLog(session.getRepositoryInfo().getName());

		Tool.printAndLog("Atompub (CMIS 1.1) EntryPoint");
		repositoryId = "A1";
		serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		session = getSession(repositoryId, serverURL);
		Tool.printAndLog(session.getRepositoryInfo().getName());

		Tool.printAndLog("Browser (CMIS 1.1) EntryPoint");
		repositoryId = "A1";
		serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
		session = getSession(repositoryId, serverURL);
		Tool.printAndLog(session.getRepositoryInfo().getName());
	}
}