package de.cmis.test.Queries;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityQuery;
import org.apache.chemistry.opencmis.commons.impl.json.parser.JSONParseException;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetQuerySupport {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		CapabilityQuery queryCapability = session.getRepositoryInfo().getCapabilities().getQueryCapability();

		if (queryCapability == null) {
			Tool.printAndLog("Repository don't support querys");
			return;
		}
		Tool.printAndLog("Query capability supported");
		Tool.printAndLog("Query capability : " + queryCapability);
	}
}