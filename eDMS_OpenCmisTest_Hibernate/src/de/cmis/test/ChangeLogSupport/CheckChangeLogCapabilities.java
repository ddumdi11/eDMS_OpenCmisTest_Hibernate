package de.cmis.test.ChangeLogSupport;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityChanges;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

/** Holt den Grad der Änderungsprotokollunterstützung durch das Repository
* <p>
* Die Schnittstelle RepositoryCapabilities stellt die Methode 'getChangesCapability' bereit, um den Grad der Änderungsprotokollunterstützung durch das Repository zu überprüfen. Die folgende Tabelle fasst die möglichen Werte zusammen.
* <p>
* Wert
* Beschreibung
* <p>
* keiner
* Das Repository unterstützt die Änderungsprotokollfunktion nicht.
* <p>
* nur Objekte
* Das Änderungsprotokoll kann nur die Objekt-IDs für geänderte Objekte im Repository und einen Hinweis auf die Art der Änderung zurückgeben, keine Details zur tatsächlichen Änderung.
* <p>
* Eigenschaften
* Das Änderungsprotokoll kann Eigenschaften und die Objekt-ID für die geänderten Objekte zurückgeben.
* <p>
* alle
* Das Änderungsprotokoll kann die Objekt-IDs für geänderte Objekte im Repository und weitere Informationen über die tatsächliche Änderung zurückgeben. 
* <p>
* -------------------------------------------------------------------------------------------------------------------
* <p>
* Get the level of change log support by the repository
* <p>
* RepositoryCapabilities interface provides 'getChangesCapability' method to check the level of change log support by the repository. Following table summarizes the possible values.
* <p>
* Value	
* Description
* <p>
* none	
* The repository does not support the change log feature.
* <p>
* objectidsonly	
* The change log can return only the object ids for changed objects in the repository and an indication of the type of change, not details of the actual change.
* <p>
* properties	
* The change log can return properties and the object id for the changed objects.
* <p>
* all	
* The change log can return the object ids for changed objects in the repository and more information about the actual change.
*/
public class CheckChangeLogCapabilities {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityChanges capabilityChanges = repoInfo.getCapabilities().getChangesCapability();

		if (capabilityChanges == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (capabilityChanges == CapabilityChanges.NONE) {
			Tool.printAndLog("The repository does not support the change log feature");
		} else if (capabilityChanges == CapabilityChanges.OBJECTIDSONLY) {
			Tool.printAndLog(
					"The change log can return only the object ids for changed objects in the repository and an indication of the type of change, not details of the actual change.");
		} else if (capabilityChanges == CapabilityChanges.PROPERTIES) {
			Tool.printAndLog("The change log can return properties and the object id for the changed objects.");
		} else if (capabilityChanges == CapabilityChanges.ALL) {
			Tool.printAndLog(
					"The change log can return the object ids for changed objects in the repository and more information about the actual change.");
		}
	}

}