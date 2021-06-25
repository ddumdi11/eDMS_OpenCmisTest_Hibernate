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

public class TestCmisGetRepositories {

	public static List<Repository> getRepositories(String serverURL) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.ATOMPUB_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public static void go() {
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		List<Repository> repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Tool.printAndLog(repository.getId() + " " + session.getRepositoryInfo().getName());
		}
	}
}