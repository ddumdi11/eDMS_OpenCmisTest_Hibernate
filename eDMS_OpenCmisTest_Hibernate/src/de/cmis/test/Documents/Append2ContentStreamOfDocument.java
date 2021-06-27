package de.cmis.test.Documents;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.repository.ObjectFactoryImpl;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.TestSetting;
import de.cmis.test.Session.SessionSingleton;

public class Append2ContentStreamOfDocument {

	private static String localFile1 = "./src/de/cmis/test/Files/app1.log";
	private static String localFile2 = "./src/de/cmis/test/Files/app2.log";

	private static void appendContentStream(Document document, File file) {
		/* Creating content stream */
		ObjectFactoryImpl objectFactory = new ObjectFactoryImpl();
		try (InputStream stream = new FileInputStream(file)) {			
			String mimetype = Files.probeContentType(file.toPath());
			// Mimetype von .log wird offenbar nicht richtig erkannt
			if (mimetype == null) {
				mimetype = "application/log";
			}
			ContentStream contentStream = objectFactory.createContentStream(file.getName(), file.length(), mimetype,
					stream);
			document.appendContentStream(contentStream, true, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void setTheContentStream(Document document) {
		/* Creating content stream */
		ObjectFactoryImpl objectFactory = new ObjectFactoryImpl();
		File file = new File(localFile1);

		try (InputStream stream = new FileInputStream(file)) {
			String mimetype = Files.probeContentType(file.toPath());
			// Mimetype von .log wird offenbar nicht richtig erkannt
			if (mimetype == null) {
				mimetype = "application/log";
			}
			System.out.println(mimetype);
			ContentStream contentStream = objectFactory.createContentStream(file.getName(), file.length(), mimetype,
					stream);
			document.setContentStream(contentStream, false, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void readTheContentsOfTheDocument(Document document) {
		ContentStream contentStream = document.getContentStream();

		try (InputStream inputStream = contentStream.getStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

			byte[] buffer = new byte[65535];
			int bytesRead = -1;

			while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
				String str = new String(buffer, 0, bytesRead);
				System.out.print(str);
			}
		} catch (IOException e) {
			System.out.println("Error occurred while processing the file content");
			System.out.println(e.getMessage());
		}

	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		/* Create the document 'application.log' */
		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "application.log");

		Folder rootFolder = (Folder) session.getObjectByPath("/Test");

		Document document = rootFolder.createDocument(properties, null, null);

		setTheContentStream(document);
		appendContentStream(document, new File(localFile2));

		readTheContentsOfTheDocument(document);

	}

}
