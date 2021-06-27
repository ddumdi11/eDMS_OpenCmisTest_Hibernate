package de.cmis.test.MetadataAndTypes;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetTypeOfObject {

	private static void printTypeInformation(ObjectType objectType) {

		String description = objectType.getDescription();
		String displayName = objectType.getDisplayName();
		String id = objectType.getId();
		String localName = objectType.getLocalName();
		String queryName = objectType.getQueryName();

		Tool.printAndLog("description = " + description);
		Tool.printAndLog("displayName = " + displayName);
		Tool.printAndLog("id = " + id);
		Tool.printAndLog("localName = " + localName);
		Tool.printAndLog("queryName = " + queryName);
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder folder = session.getRootFolder();

		ObjectType objectType = folder.getType();

		printTypeInformation(objectType);

	}
}