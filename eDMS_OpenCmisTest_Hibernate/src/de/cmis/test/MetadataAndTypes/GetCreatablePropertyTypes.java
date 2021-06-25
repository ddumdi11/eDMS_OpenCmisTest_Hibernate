package de.cmis.test.MetadataAndTypes;

import java.util.Set;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CreatablePropertyTypes;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetCreatablePropertyTypes {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		CreatablePropertyTypes creatablePropertyTypes = session.getRepositoryInfo().getCapabilities()
				.getCreatablePropertyTypes();

		Set<PropertyType> propTypes = creatablePropertyTypes.canCreate();

		for (PropertyType prop : propTypes) {
			Tool.printAndLog(prop.toString());
		}

	}
}