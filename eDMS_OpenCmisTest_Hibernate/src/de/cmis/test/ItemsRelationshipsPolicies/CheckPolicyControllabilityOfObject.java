package de.cmis.test.ItemsRelationshipsPolicies;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckPolicyControllabilityOfObject {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		// CmisObject cmisObject = session.getRootFolder();
		CmisObject cmisObject = session.getObjectByPath("/Test/My_Document-0-0");
		Boolean controllablePolicy = cmisObject.getType().isControllablePolicy();

		if (controllablePolicy == null) {
			Tool.printAndLog("Unknown (noncompliant repository)");
		} else if (controllablePolicy) {
			Tool.printAndLog("Object is controlled by the policy");
		} else {
			Tool.printAndLog("Object is not controlled by the policy");
		}

	}

}