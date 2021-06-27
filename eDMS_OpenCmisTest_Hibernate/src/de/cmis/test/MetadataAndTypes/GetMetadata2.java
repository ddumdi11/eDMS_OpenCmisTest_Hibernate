package de.cmis.test.MetadataAndTypes;

import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetMetadata2 {

	private static void printType(Tree<ObjectType> types, String space) {

		ObjectType objType = types.getItem();

		Map<String, PropertyDefinition<?>> propertyDefinitions = objType.getPropertyDefinitions();

		Tool.printAndLog("Property Definitions for " + objType.getId());
		Tool.printAndLog("****************************************************");
		for (String key : propertyDefinitions.keySet()) {
			PropertyDefinition<?> definition = propertyDefinitions.get(key);

			Tool.printAndLog("id : " + definition.getId() + ", displayName : " + definition.getDisplayName()
					+ ", propertyType : " + definition.getPropertyType() + ", defaultValue : "
					+ definition.getDefaultValue());

		}

		List<Tree<ObjectType>> objTypes = types.getChildren();

		for (Tree<ObjectType> objTypeTemp : objTypes) {
			printType(objTypeTemp, space + " ");
		}
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		List<Tree<ObjectType>> types = session.getTypeDescendants(null, -1, true);

		for (Tree<ObjectType> typeTemp : types) {
			printType(typeTemp, "");
		}

	}
}