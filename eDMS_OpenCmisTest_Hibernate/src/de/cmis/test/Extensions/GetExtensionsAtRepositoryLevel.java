package de.cmis.test.Extensions;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

/**
 * CMIS: Repository-Informationserweiterungen abrufen
 * <p>
 * Die folgenden Anweisungen werden verwendet, um die Erweiterungen auf
 * Repository-Ebene abzurufen.
 * <p>
 * RepositoryInfo repoInfo = session.getRepositoryInfo();
 * List<CmisExtensionElement>-Erweiterungen = repoInfo.getExtensions();<p>
 * <p>
 * ------------------------------------------------------------------------
 * <p>
 * CMIS: Get repository information extensions<p>
 * <p>
 * Below statements are used to get the extensions at repository level.<p>
 * <p>
 * RepositoryInfo repoInfo = session.getRepositoryInfo();
 * List<CmisExtensionElement> extensions = repoInfo.getExtensions();<p>
 */
public class GetExtensionsAtRepositoryLevel {

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

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		List<CmisExtensionElement> extensions = repoInfo.getExtensions();

		if (extensions == null) {
			Tool.printAndLog("Repository do not have extensions");
			return;
		}
		printExtensions(extensions, " ");
	}
}