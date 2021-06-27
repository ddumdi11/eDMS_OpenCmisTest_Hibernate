package de.cmis.test.IntroductionCmis;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetObjectIdOfRootFolder {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		String rootFolderId = repoInfo.getRootFolderId();

		Tool.printAndLog("rootFolderId : " + rootFolderId);

	}

}