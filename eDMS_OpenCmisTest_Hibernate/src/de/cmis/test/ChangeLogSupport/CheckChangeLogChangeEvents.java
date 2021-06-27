package de.cmis.test.ChangeLogSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.chemistry.opencmis.client.api.ChangeEvent;
import org.apache.chemistry.opencmis.client.api.ChangeEvents;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityChanges;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

/** CMIS: Änderungsprotokoll Änderungsereignisse
* <p>
* CMIS modelliert die Änderungen als 'ChangeEvent'. Change Event stellt eine Aktion dar, die an einem Objekt im Repository aufgetreten ist.
* <p>
* Die Sitzungsschnittstelle bietet die Methode 'getContentChanges', um die Änderungsereignisse von einem bestimmten Zeitpunkt abzurufen. 'getContentChanges' ist in folgenden überladenen Formularen definiert.
* <p>
* Ein Repository, das die Änderungsprotokollfunktion unterstützt, MUSS mindestens die folgenden Informationen für jedes Änderungsobjekt bereitstellen:
* <p>
* Id ObjectId Die Objekt-ID des Objekts, an dem die Änderung vorgenommen wurde.
* <p>
* Enum ChangeType Eine Aufzählung, die den Typ der Änderung angibt. Gültige Werte sind:
* erstellt - Das Objekt wurde erstellt.
* aktualisiert - Das Objekt wurde aktualisiert.
* gelöscht - Das Objekt wurde gelöscht.
* security - Die Zugriffskontrolle oder Sicherheitsrichtlinie für das Objekt wurde geändert.
* <p>
* <Eigenschaften> Eigenschaften
* Außerdem KANN das Repository für Ereignisse vom changeType "aktualisiert" optional die neuen Werte der Eigenschaften des Objekts (falls vorhanden) enthalten.
* <p>
* Die folgende Anwendung verbindet sich mit dem Inmemory-Repository und erwartet eine Eingabe vom Benutzer, um die Änderungen aus dem angegebenen Änderungsprotokolltoken abzurufen. 
* <p>
* -------------------------------------------------------------------------------------------------------------------------------
* <p>
* CMIS: Change log Change Events
* <p>
* CMIS model the changes as 'ChangeEvent'. Change Event represents an action that occurred to an object in the repository.
* <p>
* Session interface provides 'getContentChanges' method to get the change events from given point of time. 'getContentChanges' is defined in following overloaded forms.
* <p>
* A repository that supports the change log capability MUST expose at least the following information for each change object
* <p>
* Id ObjectId The object id of the object to which the change occurred.
* <p>
* Enum ChangeType An enumeration that indicates the type of the change. Valid values are:
* created - The object was created.
* updated - The object was updated.
* deleted - The object was deleted.
* security - The access control or security policy for the object were changed.
* <p>
* <Properties> properties
* Additionally, for events of changeType "updated", the repository MAY optionally include the new values of properties on the object (if any).
* <p>
* Following application connects to the inmemory repository and expects an input from the user, to get the changes from given change log token.
*/
public class CheckChangeLogChangeEvents {

	public static void go(TestSetting setting) throws IOException {
		Session session = SessionSingleton.getInstance().getSession(setting);

		CapabilityChanges capabilityChanges = session.getRepositoryInfo().getCapabilities().getChangesCapability();

		if (capabilityChanges == null) {
			Tool.printAndLog("Repository is not providing any value");
			return;
		}

		if (CapabilityChanges.NONE == capabilityChanges) {
			Tool.printAndLog("Repository is not supporitng change log");
			return;
		}

		Tool.printAndLog("capabilityChanges : " + capabilityChanges);
		ChangeEvents oldChangeEvents = session.getContentChanges(null, false, 1000);
		String previousChangeLogToken = oldChangeEvents.getLatestChangeLogToken();
		Tool.printAndLog("Change log token " + previousChangeLogToken);

		Tool.printAndLog("Press Enter to get latest change events");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		ChangeEvents changeEvents = session.getContentChanges(previousChangeLogToken, false, 1000);

		String latestChangeLogToken = changeEvents.getLatestChangeLogToken();
		Tool.printAndLog("Change log token " + latestChangeLogToken);

		for (ChangeEvent changeEvent : changeEvents.getChangeEvents()) {
			Tool.printAndLog(changeEvent.getObjectId() + ", " + changeEvent.getChangeType());
		}

	}

}