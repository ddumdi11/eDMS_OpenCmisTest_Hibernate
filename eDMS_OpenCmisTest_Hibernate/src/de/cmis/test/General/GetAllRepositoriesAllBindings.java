package de.cmis.test.General;

import java.util.List;

/**
 * Prüft die Ausgabe aller Repositories
 * über die drei möglichen Verbindungsarten Atompub (CMIS 1.0/1.1) und Browser (CMIS 1.1).
 */

import org.apache.chemistry.opencmis.client.api.Repository;

import de.cmis.test.Tool;

public class GetAllRepositoriesAllBindings {

	public static void go() {

		/**
		 * Atompub-Verbindung CMIS 1.0
		 */
		Tool.printAndLog("Verbindung Atompub (CMIS 1.0)");
		String urlToConnect = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom";

		RepositoryUtilAtomPub repoUtilAtom = new AtomPubBindingRepositoryUtil("", "", urlToConnect);

		List<Repository> repositories = repoUtilAtom.getAllRepositories();

		for (Repository repository : repositories) {
			Tool.printAndLog(repository.getName());
		}

		/**
		 * Atompub-Verbindung CMIS 1.1
		 */
		Tool.printAndLog("Verbindung Atompub (CMIS 1.1)");
		urlToConnect = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";

		repoUtilAtom = new AtomPubBindingRepositoryUtil("", "", urlToConnect);

		repositories = repoUtilAtom.getAllRepositories();

		for (Repository repository : repositories) {
			Tool.printAndLog(repository.getName());
		}

		/**
		 * Browser-Verbindung CMIS 1.1
		 */
		Tool.printAndLog("Verbindung Browser (CMIS 1.1)");
		urlToConnect = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";

		RepositoryUtilBrowser repoUtilBrowser = new BrowserBindingRepositoryUtil("", "", urlToConnect);

		repositories = repoUtilBrowser.getAllRepositories();

		for (Repository repository : repositories) {
			Tool.printAndLog(repository.getName());
		}

	}
}