package de.cmis.test.Folders;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class DeleteFolder {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder folder = (Folder) session.getObjectByPath("/Test/My_Folder-0-11");

		Tool.printAndLog("Name Of the Folder : " + folder.getName());
		Tool.printAndLog("Deleting the folder : " + folder.getName());

		folder.deleteTree(true, null, true);

		try {
			folder.refresh();
		} catch (CmisObjectNotFoundException e) {
			Tool.printAndLog("Folder is deleted : " + folder.getName());
			Tool.printAndLog(e.getMessage());
		}

	}
}