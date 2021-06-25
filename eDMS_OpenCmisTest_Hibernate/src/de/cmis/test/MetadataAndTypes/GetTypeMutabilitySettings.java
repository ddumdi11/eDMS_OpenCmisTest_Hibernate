package de.cmis.test.MetadataAndTypes;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetTypeMutabilitySettings {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder folder = session.getRootFolder();
		ObjectType objectType = folder.getType();

		boolean canCreate = objectType.getTypeMutability().canCreate();
		boolean canDelete = objectType.getTypeMutability().canDelete();
		boolean canUpdate = objectType.getTypeMutability().canUpdate();

		Tool.printAndLog("canCreate : " + canCreate);
		Tool.printAndLog("canDelete : " + canDelete);
		Tool.printAndLog("canUpdate : " + canUpdate);
	}
}