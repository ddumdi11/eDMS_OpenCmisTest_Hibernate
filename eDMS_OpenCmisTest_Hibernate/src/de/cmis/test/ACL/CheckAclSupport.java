package de.cmis.test.ACL;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityAcl;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckAclSupport {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);
		CapabilityAcl aclCapabilities = session.getRepositoryInfo().getCapabilities().getAclCapability();

		if (aclCapabilities == null) {
			Tool.printAndLog("Repository do not support acls");
			return;
		}

		Tool.printAndLog("Repository supports acls");
	}
}
