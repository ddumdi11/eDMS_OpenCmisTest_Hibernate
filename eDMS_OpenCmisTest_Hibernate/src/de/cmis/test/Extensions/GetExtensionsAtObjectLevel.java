package de.cmis.test.Extensions;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.enums.ExtensionLevel;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

/** Cmis: Erweiterungen auf Objektebene abrufen
* <p>
* Die folgenden Anweisungen werden verwendet, um die mit dem cmis-Objekt verknüpften Erweiterungen abzurufen.
* <pre>
* CmisObject cmisObject = session.getObject("134");
* List<CmisExtensionElement> extensions = cmisObject.getExtensions(ExtensionLevel.OBJECT);
* </pre><p>
* ---------------------------------------------------------------------------------------------------
* <p>
* Cmis: Get object level extensions
* <p>
* Below statements are used to get the extensions associated with cmis object.
* <pre>
* CmisObject cmisObject = session.getObject("134");
* List<CmisExtensionElement> extensions = cmisObject.getExtensions(ExtensionLevel.OBJECT);
* </pre>
*/
public class GetExtensionsAtObjectLevel {

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

		CmisObject cmisObject = session.getObject("134");

		List<CmisExtensionElement> extensions = cmisObject.getExtensions(ExtensionLevel.OBJECT);

		if (extensions == null) {
			Tool.printAndLog("Document does not have extensions");
			return;
		}
		printExtensions(extensions, " ");
	}
}