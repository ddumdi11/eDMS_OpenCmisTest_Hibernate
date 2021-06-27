package de.cmis.test.IntroductionCmis;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetAllBaseTypesOfRepository {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		ItemIterable<ObjectType> objTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : objTypes) {
			Tool.printAndLog(objectType.getId());
		}
	}

}