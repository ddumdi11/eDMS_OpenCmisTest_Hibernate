package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CanSearchAllVersionsOfDocument {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isAllVersionsSearchable = repoInfo.getCapabilities().isAllVersionsSearchableSupported();

		if (isAllVersionsSearchable == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (isAllVersionsSearchable) {
			Tool.printAndLog("You can search all the versions of a document");
		} else {
			Tool.printAndLog("You can't search all the versions");
		}
	}

}