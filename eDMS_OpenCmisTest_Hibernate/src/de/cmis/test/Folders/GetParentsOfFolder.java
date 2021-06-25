package de.cmis.test.Folders;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetParentsOfFolder {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder document = (Folder) session.getObjectByPath("/My_Folder-0-1");

		List<Folder> folders = document.getParents();

		for (Folder folder : folders) {
			Tool.printAndLog(folder.getPath());
		}

	}

}