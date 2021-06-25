package de.cmis.test.Extensions;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.enums.ExtensionLevel;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

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

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		// CmisObject cmisObj = session.getObject("L0hlbGxvLnR4dA==");
		CmisObject cmisObj = session.getObject("134");

		List<CmisExtensionElement> extensions = cmisObj.getExtensions(ExtensionLevel.PROPERTIES);

		printExtensions(extensions, " ");

	}
}