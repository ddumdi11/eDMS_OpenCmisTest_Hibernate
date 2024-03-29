package de.cmis.test.Renditions;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Rendition;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class AccessContentAssociatedWithRendition3 {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Document document = (Document) session.getObjectByPath("/Test/07_S_Verweis.docx");

		List<Rendition> renditions = document.getRenditions();

		if (renditions == null) {
			Tool.printAndLog("No renditions are existing for this object");
			return;
		}

		for (Rendition rendition : renditions) {
			String renditionDocId = rendition.getRenditionDocumentId();

			if (renditionDocId == null) {
				continue;
			}

			CmisObject cmisObject = session.getObject(renditionDocId);
		}

	}

}