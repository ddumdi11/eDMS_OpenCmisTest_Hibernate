package de.cmis.test.SecondaryTypes;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckSecondaryTypeSupport {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		ItemIterable<ObjectType> objectTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : objectTypes) {
			if ("cmis:secondary".equals(objectType.getId())) {
				Tool.printAndLog("Secondary types are supported by the repository");
				return;
			}
		}

		Tool.printAndLog("Secondary types are not supported");
	}

}