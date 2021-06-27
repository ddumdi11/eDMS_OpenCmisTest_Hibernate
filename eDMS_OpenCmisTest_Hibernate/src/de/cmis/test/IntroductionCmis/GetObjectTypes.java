package de.cmis.test.IntroductionCmis;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetObjectTypes {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder folder = session.getRootFolder();

		List<Property<?>> properties = folder.getProperties();

		Tool.printAndLog("propertyId | displayName | value");
		for (Property<?> property : properties) {
			Tool.printAndLog(property.getId() + " | " + property.getDisplayName() + " | " + property.getValue());
		}

	}

}