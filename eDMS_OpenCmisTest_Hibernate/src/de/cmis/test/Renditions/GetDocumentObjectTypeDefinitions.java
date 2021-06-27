package de.cmis.test.Renditions;

import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetDocumentObjectTypeDefinitions {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		ObjectType objectType = session.getTypeDefinition("cmis:document");

		Map<String, PropertyDefinition<?>> propDefinitions = objectType.getPropertyDefinitions();

		for (String property : propDefinitions.keySet()) {
			Tool.printAndLog("\n****************************************");
			Tool.printAndLog(property);
			Tool.printAndLog("****************************************");

			PropertyDefinition<?> propDefinition = propDefinitions.get(property);

			Tool.printAndLog("Property Type : " + propDefinition.getPropertyType());
			Tool.printAndLog("Inherited : " + propDefinition.isInherited());
			Tool.printAndLog("Required : " + propDefinition.isRequired());
			Tool.printAndLog("Cardinality : " + propDefinition.getCardinality());
			Tool.printAndLog("Updatability : " + propDefinition.getUpdatability());
			Tool.printAndLog("Choices : " + propDefinition.getChoices());
			Tool.printAndLog("Open Choice : " + propDefinition.isOpenChoice());
			Tool.printAndLog("Queryable : " + propDefinition.isQueryable());
			Tool.printAndLog("Orderable : " + propDefinition.isOrderable());

		}

	}

}