package de.cmis.test.Documents;

import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class TestUnfilingSupport {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		boolean isUnfilingSupprted = session.getRepositoryInfo().getCapabilities().isUnfilingSupported();

		if (!isUnfilingSupprted) {
			Tool.printAndLog("unfiling documents are not supported");
			return;
		}

		Tool.printAndLog("unfiling doucments are supported by the repository");

	}
}