package de.cmis.test.ItemsRelationshipsPolicies;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckItemTypeSupport {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		ItemIterable<ObjectType> objectTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : objectTypes) {
			if ("cmis:item".equals(objectType.getId())) {
				Tool.printAndLog("Repository supports item type");
				return;
			}
		}

		Tool.printAndLog("Repository do not support item type");
	}

}