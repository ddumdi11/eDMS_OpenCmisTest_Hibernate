package de.cmis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;

import de.cmis.test.Session.SessionSingleton;

public class ResetOpenCmisServer {

	private static File inputFile = new File("./src/de/cmis/test/Files/data.txt");

	private static void createDefaultFolderStructureInFolder(Session session, Folder folder, int repeat, int count)
			throws IOException {

		if (repeat > 0) {
			// 3 Dokumente mit demselben Inhalt im Ordner erstellen
			String mimeType = Files.probeContentType(inputFile.toPath());
			InputStream inputStream = new FileInputStream(inputFile);
			ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
					inputFile.length(), mimeType, inputStream);
			for (int j = 0; j < 3; j++) {
				Map<String, String> propertiesDoc = new HashMap<>();
				propertiesDoc.put("cmis:objectTypeId", "cmis:document");
				propertiesDoc.put("cmis:name", "My_Document-" + count + "-" + j);
				try {
					Document document = folder.createDocument(propertiesDoc, contentStream, null);
				} catch (Exception e) {
					System.out.println("Dokument lässt sich nicht erstellen (oder anderes Problem).");
				}
			}
			// 3 Ordner im Ordner erstellen
			for (int i = 0; i < 3; i++) {
				Map<String, String> propertiesFolder = new HashMap<>();
				propertiesFolder.put("cmis:objectTypeId", "cmis:folder");
				propertiesFolder.put("cmis:name", "My_Folder-" + count + "-" + i);
				try {
					Folder subFolder = folder.createFolder(propertiesFolder);
					createDefaultFolderStructureInFolder(session, subFolder, repeat - 1, count + 1);
				} catch (Exception e) {
					System.out.println("Ordner lässt sich nicht erstellen (oder anderes Problem).");
				}

			}
		}

	}

	private static void deleteTestFolder(Session session) {
		// Den kompletten Test-Ordner-Inhalt löschen (falls schon vorhanden)
		try {
			Folder testFolder = (Folder) session.getObjectByPath("/Test");
			ItemIterable<CmisObject> cmisObjects = testFolder.getChildren();
			for (CmisObject cmisObject : cmisObjects) {
				if (cmisObject instanceof Folder) {
					Folder toDelete = (Folder) session.getObjectByPath(((Folder) cmisObject).getPath());
					toDelete.deleteTree(true, UnfileObject.DELETE, true);
				} else {
					cmisObject.delete();
				}
			}
			testFolder.deleteTree(true, null, false);
			testFolder.refresh();
			System.out.println("Ordner 'Test' wurde gelöscht.");
		} catch (Exception e) {
			System.out.println("Ordner 'Test' im Rootfolder nicht vorhanden oder lässt sich nicht löschen.");
		}
	}

	private static void createTestFolder(Session session) throws IOException {
		// Ordner-Struktur neu erzeugen
		Folder rootFolder = session.getRootFolder();
		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:folder");
		properties.put("cmis:name", "Test");
		Folder testFolder = rootFolder.createFolder(properties);
		createDefaultFolderStructureInFolder(session, testFolder, 3, 0);
	}

	public static void go(TestSetting setting) throws IOException {
		Session session = SessionSingleton.getInstance().getSession(setting);

		deleteTestFolder(session);
		createTestFolder(session);

	}

}
