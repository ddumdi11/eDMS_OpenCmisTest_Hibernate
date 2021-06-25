package de.cmis.test.Folders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Tool;

public class TraversThroughRootFolderHirarchy {

	public static List<Repository> getRepositories(String serverUrl) {
		Map<String, String> parameters = new HashMap<>();

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		if (serverUrl.contains("atom")) {
			parameters.put(SessionParameter.ATOMPUB_URL, serverUrl);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		} else if (serverUrl.contains("browser")) {
			parameters.put(SessionParameter.BROWSER_URL, serverUrl);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
		}

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public static void printFolder(Folder folder, String tab) {
		Tool.printAndLog(tab + folder.getName());

		ItemIterable<CmisObject> cmisObjects = folder.getChildren();

		for (CmisObject cmisObject : cmisObjects) {
			if (cmisObject instanceof Folder) {
				printFolder((Folder) cmisObject, tab + " ");
			}
			Tool.printAndLog(" " + tab + cmisObject.getName());
		}

	}

	public static void go() {
		Tool.printAndLog("Repository-Suche über EntryPoint Atompub (CMIS 1.0) OpenCmisServer");
		String serverUrl = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom";
		List<Repository> repositories = getRepositories(serverUrl);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Folder rootFolder = session.getRootFolder();
			printFolder(rootFolder, "");

		}

		Tool.printAndLog("Repository-Suche über EntryPoint Atompub (CMIS 1.1) OpenCmisServer");
		serverUrl = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		repositories = getRepositories(serverUrl);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Folder rootFolder = session.getRootFolder();
			printFolder(rootFolder, "");

		}

		Tool.printAndLog("Repository-Suche über EntryPoint Atompub (CMIS 1.1) Browser");
		serverUrl = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
		repositories = getRepositories(serverUrl);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Folder rootFolder = session.getRootFolder();
			printFolder(rootFolder, "");

		}
	}
}