package de.cmis.test.ItemsRelationshipsPolicies;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.RelationshipType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckAllowedSourceAndTargetTypes {

	private static void printTypes(List<ObjectType> allowedTypes) {
		for (ObjectType objectType : allowedTypes) {
			Tool.printAndLog(objectType.toString());
		}
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RelationshipType relationShipType = (RelationshipType) session.getTypeDefinition("cmis:relationship");

		List<ObjectType> allowedSourceTypes = relationShipType.getAllowedSourceTypes();
		List<ObjectType> allowedTargetTypes = relationShipType.getAllowedTargetTypes();

		Tool.printAndLog("Allowed source types");
		printTypes(allowedSourceTypes);

		Tool.printAndLog("Allowed target types");
		printTypes(allowedTargetTypes);

	}

}