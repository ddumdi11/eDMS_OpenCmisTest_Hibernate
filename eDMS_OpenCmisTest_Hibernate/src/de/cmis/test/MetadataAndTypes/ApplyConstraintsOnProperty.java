package de.cmis.test.MetadataAndTypes;

import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class ApplyConstraintsOnProperty {

	public static void printPropertyDefinitions(Map<String, PropertyDefinition<?>> propertyDefinitions) {

		for (String key : propertyDefinitions.keySet()) {
			Tool.printAndLog("\n\nGeneric Constraints for the property : " + key);
			Tool.printAndLog("************************************************");

			PropertyDefinition<?> propertyDefinition = propertyDefinitions.get(key);

			boolean isRequired = propertyDefinition.isRequired();
			List<?> choices = propertyDefinition.getChoices();

			Tool.printAndLog("isRequired : " + isRequired);
			Tool.printAndLog("isOpenChoice : " + propertyDefinition.isOpenChoice());
			Tool.printAndLog("choices : " + choices);
		}
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder folder = session.getRootFolder();
		ObjectType objectType = folder.getType();
		Map<String, PropertyDefinition<?>> propertyDefinitions = objectType.getPropertyDefinitions();

		printPropertyDefinitions(propertyDefinitions);
	}
}