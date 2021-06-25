package de.cmis.test.Properties;

import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.FolderType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetPropertiesOfFolderType {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder folder = session.getRootFolder();

		FolderType folderType = folder.getFolderType();

		Map<String, PropertyDefinition<?>> propertyDefintions = folderType.getPropertyDefinitions();

		for (String key : propertyDefintions.keySet()) {
			PropertyDefinition propDefinition = propertyDefintions.get(key);
			Tool.printAndLog(propDefinition.toString());
		}

	}

}