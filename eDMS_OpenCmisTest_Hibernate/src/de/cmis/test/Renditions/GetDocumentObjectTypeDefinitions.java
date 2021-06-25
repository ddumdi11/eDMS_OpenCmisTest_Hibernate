package de.cmis.test.Renditions;

import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.TestSetting;
import de.cmis.test.Session.SessionSingleton;

public class GetDocumentObjectTypeDefinitions {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		ObjectType objectType = session.getTypeDefinition("cmis:document");

		Map<String, PropertyDefinition<?>> propDefinitions = objectType.getPropertyDefinitions();

		for (String property : propDefinitions.keySet()) {
			System.out.println("\n****************************************");
			System.out.println(property);
			System.out.println("****************************************");

			PropertyDefinition<?> propDefinition = propDefinitions.get(property);

			System.out.println("Property Type : " + propDefinition.getPropertyType());
			System.out.println("Inherited : " + propDefinition.isInherited());
			System.out.println("Required : " + propDefinition.isRequired());
			System.out.println("Cardinality : " + propDefinition.getCardinality());
			System.out.println("Updatability : " + propDefinition.getUpdatability());
			System.out.println("Choices : " + propDefinition.getChoices());
			System.out.println("Open Choice : " + propDefinition.isOpenChoice());
			System.out.println("Queryable : " + propDefinition.isQueryable());
			System.out.println("Orderable : " + propDefinition.isOrderable());

		}

	}

}