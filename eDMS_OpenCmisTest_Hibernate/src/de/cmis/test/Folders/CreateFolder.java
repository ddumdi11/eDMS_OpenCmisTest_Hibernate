package de.cmis.test.Folders;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CreateFolder {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder rootFolder = (Folder) session.getObjectByPath("/Test");

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:folder");
		properties.put("cmis:name", "My_Folder-0-11");

		Folder folder = rootFolder.createFolder(properties);

		Tool.printAndLog("Name Of the Folder " + folder.getName());
		Tool.printAndLog("Path Of the Folder " + folder.getPaths().get(0));
	}
}