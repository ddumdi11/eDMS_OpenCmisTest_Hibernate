package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckVersioningCapabilities {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isVersionSpecificFilingSupported = repoInfo.getCapabilities().isVersionSpecificFilingSupported();

		if (isVersionSpecificFilingSupported == null) {
			Tool.printAndLog("Repository does not provide this value");
		} else if (isVersionSpecificFilingSupported) {
			Tool.printAndLog("Repository is supporting version specific filing");
		} else {
			Tool.printAndLog("Repository is not supporting version specific filing");
		}
	}

}