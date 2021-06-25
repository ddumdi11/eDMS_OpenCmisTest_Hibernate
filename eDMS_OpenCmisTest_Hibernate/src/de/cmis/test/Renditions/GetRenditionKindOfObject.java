package de.cmis.test.Renditions;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Rendition;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Session.SessionSingleton;

public class GetRenditionKindOfObject {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Document document = (Document) session.getObjectByPath("/sampleDoc.json");

		List<Rendition> renditions = document.getRenditions();

		if (renditions == null) {
			System.out.println("No renditions are existed for this object");
			return;
		}

		for (Rendition rendition : renditions) {
			String renditionKind = rendition.getKind();
			System.out.println("renditionKind : " + renditionKind);
		}

	}

}