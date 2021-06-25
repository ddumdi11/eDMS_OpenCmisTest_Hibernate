package de.cmis.test;

import java.io.IOException;


import de.cmis.test.Documents.CreateEmptyDocumentInRootFolder;
import de.cmis.test.Documents.CreateUnfiledDocumentInRootFolder;
import de.cmis.test.Documents.Versioning;
import de.cmis.test.General.Test;
import de.cmis.test.General.TestCmisGetAclOfRootFolder;
import de.cmis.test.General.TestCmisGetAllPropertyTypes;
import de.cmis.test.General.TestCmisGetBasicRepositoryInfo;
import de.cmis.test.General.TestCmisGetBasicTypes;
import de.cmis.test.General.TestCmisGetIdOfRootFolder;
import de.cmis.test.General.TestCmisGetRepositories;
import de.cmis.test.General.TestCmisGetSession;

public class AllTestsMain {

	public static void main(String[] args) throws IOException {
		
		// Hibernate Session für Logging erzeugen
		Tool testSession = new Tool();
		testSession.openSession();
		
		// Allgemeine Tests		
		Test.go();
		TestCmisGetAclOfRootFolder.go();
		TestCmisGetAllPropertyTypes.go();
		TestCmisGetBasicRepositoryInfo.go();
		TestCmisGetBasicTypes.go();
		TestCmisGetIdOfRootFolder.go();
		TestCmisGetRepositories.go();
		TestCmisGetSession.go();		
		
		// Dokumente Tests		
		CreateEmptyDocumentInRootFolder.go();
		CreateUnfiledDocumentInRootFolder.go();
		Versioning.go();
		
		
		// Hibernate Session für Logging schließen
		testSession.closeSession();
		

	}

}
