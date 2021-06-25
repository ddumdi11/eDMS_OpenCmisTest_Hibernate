package de.cmis.test;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.Action;

import de.cmis.test.Session.SessionSingleton;

public class CleanUpAlfresco {

	public static void go(String[] defaultSetting) throws IOException {
		// Verbindung zu Alfresco mit den Default-Einstellungen
		Session session = SessionSingleton.getInstance().getSession(defaultSetting);

		try {
			// Angelegtes Dokument "sample1.txt" im RootFolder löschen
			Document document = (Document) session.getObjectByPath("/sample1.txt");
			/* Checking for permissions, whether user has permission to delete or not. */
			if (!document.getAllowableActions().getAllowableActions().contains(Action.CAN_DELETE_OBJECT)) {
				Tool.printAndLog("User don't have permission to delete the object");
			}
			document.delete();
		} catch (Exception e) {
			System.out.println("Dokument existiert nicht im RootFolder (oder anderes Problem).");
		}

	}

}
