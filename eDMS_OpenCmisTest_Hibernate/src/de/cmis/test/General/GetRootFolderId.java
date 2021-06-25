package de.cmis.test.General;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetRootFolderId {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);
		Folder folder = session.getRootFolder();
		Tool.printAndLog("Zu beachten: Jedes CMIS-Objekt hat eine eindeutige ID!\nHier ist die Id des RootFolder/Wurzelverzeichnis:");
		Tool.printAndLog("id : " + folder.getId());
		
	}
}
