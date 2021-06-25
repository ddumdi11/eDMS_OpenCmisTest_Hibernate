package de.cmis.test.Documents;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Rendition;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class AccessContentAssociatedWithRendition {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Document document = (Document) session.getObjectByPath("/sampleDoc.json");

		List<Rendition> renditions = document.getRenditions();

		if (renditions == null) {
			Tool.printAndLog("No renditions are existed for this object");
			return;
		}

		for (Rendition rendition : renditions) {
			String rendtionStreamId = rendition.getStreamId();
			ContentStream contentStream = session.getContentStream(new ObjectIdImpl(document.getId()), rendtionStreamId,
					null, null);
		}

	}

}