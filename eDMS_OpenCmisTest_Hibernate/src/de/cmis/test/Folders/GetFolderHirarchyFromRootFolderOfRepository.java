package de.cmis.test.Folders;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;

import de.cmis.test.TestSetting;
import de.cmis.test.Session.SessionSingleton;

public class GetFolderHirarchyFromRootFolderOfRepository {

	public static void printHierarchy(List<Tree<FileableCmisObject>> objects, String space) {

		for (Tree<FileableCmisObject> obj : objects) {
			FileableCmisObject fileableObj = obj.getItem();

			System.out.println(space + fileableObj.getName());
			printHierarchy(obj.getChildren(), " " + space);
		}
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Folder rootFolder = session.getRootFolder();

		List<Tree<FileableCmisObject>> fileableCmisObjects = rootFolder.getDescendants(-1);

		printHierarchy(fileableCmisObjects, "");
	}

}