package de.cmis.test.SecondaryTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class RemoveSecondaryType {

	private static void printDocument(CmisObject cmisObject) {
		Tool.printAndLog("***********************************************");
		Tool.printAndLog("contact us : " + cmisObject.getPropertyValue("cbc:contactUs"));
		Tool.printAndLog("Help : " + cmisObject.getPropertyValue("cbc:help"));
		Tool.printAndLog("confidentialValue : " + cmisObject.getPropertyValue("cbc:confidentialLevel"));

		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);

		Tool.printAndLog("Secondary types are");
		for (String secondaryType : secondaryTypes) {
			Tool.printAndLog(secondaryType);
		}
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		CmisObject cmisObject = session.getObjectByPath("/Test/emptyDocument2.txt");

		Tool.printAndLog("Before removing secondary type 'cbc:confidentialDocs'");
		printDocument(cmisObject);

		/* Get current secondary types */
		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		if (secondaryTypes == null) {
			Tool.printAndLog("No secondary types associated to this object");
			return;
		}

		Map<String, Object> properties = new HashMap<String, Object>();

		/* Remove the new secondary type */
		secondaryTypes.remove("cbc:confidentialDocs");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		/* update properties */
		cmisObject.updateProperties(properties);

		Tool.printAndLog("\nAfter removing secondary type 'cbc:confidentialDocs'");
		printDocument((Document) cmisObject);
	}

}