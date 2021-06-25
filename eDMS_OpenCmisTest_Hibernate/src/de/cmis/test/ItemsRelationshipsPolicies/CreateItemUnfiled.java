package de.cmis.test.ItemsRelationshipsPolicies;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Item;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CreateItemUnfiled {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "My Demo unfiled item");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:item");

		ObjectId itemId = session.createItem(properties, null);

		Item item = (Item) session.getObject(itemId);

		Tool.printAndLog((PropertyIds.NAME + ": " + item.getPropertyValue(PropertyIds.NAME)));
		Tool.printAndLog((PropertyIds.OBJECT_TYPE_ID + ": " + item.getPropertyValue(PropertyIds.OBJECT_TYPE_ID)));

	}

}