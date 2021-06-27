package de.cmis.test.Extensions;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.enums.ExtensionLevel;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

/** Cmis: Erweiterungen auf Property-Ebene abrufen
* <p>
* Auf der Serverseite sollte der Server Erweiterungen auf Eigenschaftsebene hinzufügen. Beim Senden der Antwort an den Client sollte der Server den Objektdaten Erweiterungen auf Eigenschaftsebene hinzufügen.
* <p><pre>
*          private statische void setExtensions(ObjectData objData) {
*                   CmisExtensionElement leafExtension1 = new CmisExtensionElementImpl("", "confidentialLevel", null,
*                                     "geheim");
*                   CmisExtensionElement leafExtension2 = new CmisExtensionElementImpl("", "rootFolderId", null, "folder_1234");
*                   CmisExtensionElement leafExtension3 = new CmisExtensionElementImpl("", "classification", null, "public");
*
*                   List<CmisExtensionElement> list = new ArrayList<>();
*                   list.add(leafExtension1);
*                   list.add(leafExtension2);
*                   list.add(leafExtension3);
*
*                   CmisExtensionElement parentExtension = new CmisExtensionElementImpl("", "parentExtension", null, list);
*
*                   List<CmisExtensionElement> extensions = Collections.singletonList(parentExtension);
*
*                   Properties props = objData.getProperties();
*
*                   props.setExtensions(Erweiterungen);
*
*          }
* </pre>         
* Das obige Snippet fügt die Erweiterungen zum Objekt auf Eigenschaftsebene hinzu.
* <p>
* Wie liest man die Erweiterungen auf Property-Ebene?
* Das folgende Snippet wird verwendet, um die Erweiterungen auf Eigenschaftsebene des cmis-Objekts abzurufen.
* <p><pre>
* Session session = getSession();
* CmisObject cmisObj = session.getObject("L0hlbGxvLnR4dA==");
* List<CmisExtensionElement> extensions = cmisObj.getExtensions(ExtensionLevel.PROPERTIES);
* </pre><p>
* --------------------------------------------------------------------------------------------------------
* <p>
* Cmis: Get property level extensions
* <p>
* At the server end, server should add property level extensions. While sending the response to the client, server should add property level extensions to object data.
* <p><pre>
*          private static void setExtensions(ObjectData objData) {
*                   CmisExtensionElement leafExtension1 = new CmisExtensionElementImpl("", "confidentialLevel", null,
*                                     "confidential");
*                   CmisExtensionElement leafExtension2 = new CmisExtensionElementImpl("", "rootFolderId", null, "folder_1234");
*                   CmisExtensionElement leafExtension3 = new CmisExtensionElementImpl("", "classification", null, "public");
*
*                   List<CmisExtensionElement> list = new ArrayList<>();
*                   list.add(leafExtension1);
*                   list.add(leafExtension2);
*                   list.add(leafExtension3);
*
*                   CmisExtensionElement parentExtension = new CmisExtensionElementImpl("", "parentExtension", null, list);
*
*                   List<CmisExtensionElement> extensions = Collections.singletonList(parentExtension);
*                   Properties props = objData.getProperties();
*                   props.setExtensions(extensions);
*
*          }
* </pre>         
* Above snippet adds the extensions to object at property level.
* <p>
* How to read the property level extensions?
* Below snippet is used to get the property level extensions of cmis object.
* <p><pre>
* Session session = getSession();
* CmisObject cmisObj = session.getObject("L0hlbGxvLnR4dA==");
* List<CmisExtensionElement> extensions = cmisObj.getExtensions(ExtensionLevel.PROPERTIES);
* </pre>
*/
public class GetExtensionsAtPropertyLevel {

	private static void printExtensions(List<CmisExtensionElement> extensions, String space) {

		for (CmisExtensionElement extension : extensions) {
			List<CmisExtensionElement> extensionChildren = extension.getChildren();

			if (extensionChildren == null || extensionChildren.isEmpty()) {
				String name = extension.getName();
				String value = extension.getValue();
				Tool.printAndLog(name + " : " + value);

			} else {
				printExtensions(extensionChildren, space + " ");
			}
		}
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		// CmisObject cmisObj = session.getObject("L0hlbGxvLnR4dA==");
		CmisObject cmisObj = session.getObject("134");

		List<CmisExtensionElement> extensions = cmisObj.getExtensions(ExtensionLevel.PROPERTIES);

		printExtensions(extensions, " ");

	}
}