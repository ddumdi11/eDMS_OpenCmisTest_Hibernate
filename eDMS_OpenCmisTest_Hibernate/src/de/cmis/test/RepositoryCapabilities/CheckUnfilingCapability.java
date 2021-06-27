package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckUnfilingCapability {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isUnfilingSupported = repoInfo.getCapabilities().isUnfilingSupported();

		if (isUnfilingSupported == null) {
			Tool.printAndLog("Repository does not provide this value");
		} else if (isUnfilingSupported) {
			Tool.printAndLog("Repository is supporting unfiling");
		} else {
			Tool.printAndLog("Repository is not supporting unfiling");
		}
	}

}