package de.cmis.test.General;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetBasicTypes {

	public static void go() {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ItemIterable<ObjectType> baseTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : baseTypes) {
			Tool.printAndLog(objectType.toString());
		}

	}

}