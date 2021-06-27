package de.cmis.test.Extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.CmisExtensionElementImpl;

import de.cmis.test.Tool;

/** CMIS: CmisExtensionElement: cmis-Erweiterungen erstellen
* <p>
* openCMIS bietet eine CmisExtensionElement-Schnittstelle zum Definieren von cmis-Erweiterungen. Die CmisExtensionElementImpl-Klasse implementiert die CmisExtensionElement-Schnittstelle.
* <p>
* Die CmisExtensionElementImpl-Klasse stellt die folgenden Konstruktoren bereit, um Objekte von CmisExtensionElement zu definieren.
* <p><pre>
* public CmisExtensionElementImpl(String-Namespace, String-Name, Map<String, String>-Attribute, String-Wert)
* public CmisExtensionElementImpl(String-Namespace, String-Name, Map<String, String>-Attribute,List<CmisExtensionElement>-Kinder)
* public CmisExtensionElementImpl(CmisExtensionElement-Element)
* </pre><p>
* Jedes cmis-Erweiterungselement hat einen Namen und damit verbundene optionale Attribute. Die Browserbindung unterstützt keinen Namensraum und keine Attribute der cmis-Erweiterung (da json keine Attribute und kein Namensraumkonzept hat).
* <p>
* Ein cmis-Erweiterungselement kann entweder untergeordnete cmis-Erweiterungselemente (oder) einen ihm zugeordneten Wert haben. 
* <p>
* -------------------------------------------------------------------------------------------------------------------
* <p>
* CMIS: CmisExtensionElement: Create cmis extensions
* <p>
* openCMIS provides CmisExtensionElement interface to define cmis extensions. CmisExtensionElementImpl class implements CmisExtensionElement interface.
* <p>
* CmisExtensionElementImpl class provide below constructors to define objects of CmisExtensionElement.
* <p><pre>
* public CmisExtensionElementImpl(String namespace, String name, Map<String, String> attributes, String value)
* public CmisExtensionElementImpl(String namespace, String name, Map<String, String> attributes,List<CmisExtensionElement> children)
* public CmisExtensionElementImpl(CmisExtensionElement element)
* </pre>
* Every cmis extension element has a name and optional attributes associated with it. Browser binding do not support name space and attributes of cmis extension (Since json do not have attributes and namespace concept).
* <p>
* A cmis extension element can have either children cmis extension elements (or) a value associated with it.
* <p>
*/
public class CreateExtension {

	private static void printExtensions(List<CmisExtensionElement> extensions, String space) {

		for (CmisExtensionElement extension : extensions) {
			List<CmisExtensionElement> extensionChildren = extension.getChildren();

			if (extensionChildren == null || extensionChildren.isEmpty()) {
				String name = extension.getName();
				String value = extension.getValue();
				Tool.printAndLog(space + name + " : " + value);

			} else {
				Tool.printAndLog(space + extension.getName());
				printExtensions(extensionChildren, space + " ");
			}
		}
	}

	public static void go() {
		CmisExtensionElement leafExtension1 = new CmisExtensionElementImpl("", "confidentialLevel", null,
				"confidential");
		CmisExtensionElement leafExtension2 = new CmisExtensionElementImpl("", "rootFolderId", null, "folder_1234");
		CmisExtensionElement leafExtension3 = new CmisExtensionElementImpl("", "classification", null, "public");

		List<CmisExtensionElement> list = new ArrayList<>();
		list.add(leafExtension1);
		list.add(leafExtension2);
		list.add(leafExtension3);

		CmisExtensionElement parentExtension = new CmisExtensionElementImpl("", "parentExtension", null, list);

		List<CmisExtensionElement> extensions = Collections.singletonList(parentExtension);

		printExtensions(extensions, " ");
	}
}