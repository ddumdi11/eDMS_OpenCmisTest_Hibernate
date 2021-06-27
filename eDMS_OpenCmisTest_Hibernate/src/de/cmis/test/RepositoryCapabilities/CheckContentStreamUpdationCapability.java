package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityContentStreamUpdates;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckContentStreamUpdationCapability {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityContentStreamUpdates capabilityContentStreamUpdates = repoInfo.getCapabilities()
				.getContentStreamUpdatesCapability();

		if (capabilityContentStreamUpdates == null) {
			Tool.printAndLog("Repository is not providing any value");
		} else if (capabilityContentStreamUpdates == CapabilityContentStreamUpdates.NONE) {
			Tool.printAndLog("Content stream will not be updated");
		} else if (capabilityContentStreamUpdates == CapabilityContentStreamUpdates.ANYTIME) {
			Tool.printAndLog("Content stream can be updated at any time");
		} else if (capabilityContentStreamUpdates == CapabilityContentStreamUpdates.PWCONLY) {
			Tool.printAndLog("Content stream can be updated only when the document is checked out");
		}

	}

}