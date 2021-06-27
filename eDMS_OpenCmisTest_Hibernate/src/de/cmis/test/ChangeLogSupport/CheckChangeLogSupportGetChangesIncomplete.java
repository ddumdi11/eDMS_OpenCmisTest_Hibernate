package de.cmis.test.ChangeLogSupport;

import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

/** CMIS: Unterstützung für Änderungsprotokolle
* <p>
* Durch die Verwendung des CMIS-"Änderungsprotokoll"-Mechanismus kann die Anwendung den Satz von Änderungen erkennen, die zu einem bestimmten Zeitpunkt an im Repository gespeicherten Objekten aufgetreten sind.
* <p>
* Änderungsprotokoll ist eine Sammlung von Änderungsereignissen. ein Änderungsereignis entspricht einem cmis-Objekt.
* <p>
* Hinweis<p>
* Gemäß der Spezifikation MÜSSEN die Änderungsereignisse im Änderungsprotokoll ab dem Zeitpunkt des Eintretens des Änderungsereignisses in aufsteigender Reihenfolge zurückgegeben werden.
* <p>
* Ist Änderungsprotokoll kann alle Änderungen geben?
* Das hängt vom Repository ab. Repositorys können einen Eintrag für jede jemals an Inhalten im Repository vorgenommene Änderung haben oder nur einen Eintrag für alle Änderungen zurückgeben, die seit einem bestimmten Zeitpunkt vorgenommen wurden.
* <p>
* Die RepositoryInfo-Schnittstelle bietet die Methode 'getChangesIncomplete', um zu überprüfen, ob die Einträge im Änderungsprotokoll unvollständig oder vollständig sind.
* <p>
* Boolesche getChangesIncomplete()
* Gibt true zurück, wenn die Änderungen unvollständig sind, false, wenn die Änderungen abgeschlossen sind, oder null, wenn das Repository dieses Flag nicht bereitgestellt hat 
* <p>
* ---------------------------------------------------------------------------------------------------------------------
* <p>
* CMIS: Change log support
* <p>
* By using CMIS 'change log' mechanism, application can discover the set of changes that have occurred to objects stored in the repository from a given point of time.
* <p>
* Change log is a collection change events. one change event corresponds to one cmis object.
* <p>
* Note<p>
* As per the specification, the change events in the change log MUST be returned in ascending order from the time when the change event occurred.
* <p>
* Is change log can give all the changes?
* It depends on the repository. Repositories can have an entry for every change ever made to content in the repository, or may only be able to return an entry for all changes made since a particular point in time.
* <p>
* RepositoryInfo interface provides 'getChangesIncomplete' method, to check whether the entries in the change log are incomplete or complete.
* <p>
* Boolean getChangesIncomplete()
* Return true if the changes are incomplete, false if the changes are complete, or null if the repository didn't provide this flag
*/
public class CheckChangeLogSupportGetChangesIncomplete {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Boolean isChangesInComplete = session.getRepositoryInfo().getChangesIncomplete();

		if (isChangesInComplete) {
			Tool.printAndLog("Changes in the changelog are incomplete");
			return;
		}

		Tool.printAndLog("Changes in the change log are complete");
	}

}