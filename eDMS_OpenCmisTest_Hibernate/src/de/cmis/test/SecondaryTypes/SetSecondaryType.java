package de.cmis.test.SecondaryTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class SetSecondaryType {

	private static void printDocument(Document document) {
		String contactUs = document.getPropertyValue("cbc:contactUs");
		String help = document.getPropertyValue("cbc:help");

		Tool.printAndLog("name : " + document.getName());
		Tool.printAndLog("id : " + document.getId());
		Tool.printAndLog("version series id : " + document.getVersionSeriesId());
		Tool.printAndLog("Version Label : " + document.getVersionLabel());
		Tool.printAndLog("Checked out by : " + document.getVersionSeriesCheckedOutBy());
		Tool.printAndLog("Content length : " + document.getContentStreamLength());

		Tool.printAndLog("contact us : " + contactUs);
		Tool.printAndLog("Help : " + help);
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder rootFolder = (Folder) session.getObjectByPath("/Test");

		Map<String, Object> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "emptyDocument2.txt");

		List<String> secondaryTypes = new ArrayList<String>();
		secondaryTypes.add("cbc:secondaryType");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		properties.put("cbc:contactUs", "https://self-learning-java-tutorial.blogspot.com");
		properties.put("cbc:help", "Java Tutorial blogspot");

		Document document = rootFolder.createDocument(properties, null, null);
		printDocument(document);
	}

}