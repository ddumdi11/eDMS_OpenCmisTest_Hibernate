package de.cmis.test.Folders;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetFolderByPath {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		CmisObject cmisObject = session.getObjectByPath("/My_Folder-0-1");

		Tool.printAndLog("Name : " + cmisObject.getName());
		Tool.printAndLog("Type : " + cmisObject.getType());

	}

}