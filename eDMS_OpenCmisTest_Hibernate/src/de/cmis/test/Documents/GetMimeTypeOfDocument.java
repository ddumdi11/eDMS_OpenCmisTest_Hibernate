package de.cmis.test.Documents;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetMimeTypeOfDocument {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Document document = (Document) session.getObjectByPath("/Test/sampleDoc.json");

		ContentStream contentStream = document.getContentStream();

		if (contentStream == null) {
			Tool.printAndLog("There is no content stream associated with this document");
			return;
		}

		String mimeType = contentStream.getMimeType();

		Tool.printAndLog("mimeType : " + mimeType);

	}

}