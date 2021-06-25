package de.cmis.test.Documents;

import java.util.HashMap;

import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CreateEmptyDocumentInRootFolder {

	static Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

	public static void go() {
		Folder rootFolder = session.getRootFolder();

		// Dokument löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/emptyDocument.txt");
			Tool.printAndLog("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			Tool.printAndLog("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			Tool.printAndLog("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "emptyDocument.txt");

		Document document = rootFolder.createDocument(properties, null, null);

		Tool.printAndLog("Name Of the Document " + document.getName());
		Tool.printAndLog("Path Of the Document " + document.getPaths().get(0));
	}
}