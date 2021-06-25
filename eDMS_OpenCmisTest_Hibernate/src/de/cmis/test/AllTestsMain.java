package de.cmis.test;

import java.io.IOException;


import de.cmis.test.Documents.CreateEmptyDocumentInRootFolder;
import de.cmis.test.Documents.CreateUnfiledDocumentInRootFolder;
import de.cmis.test.Documents.Versioning;
import de.cmis.test.General.GetAllRepositoriesAllBindings;
import de.cmis.test.General.TestCmisGetAclOfRootFolder;
import de.cmis.test.General.TestCmisGetAllPropertyTypes;
import de.cmis.test.General.TestCmisGetBasicRepositoryInfo;
import de.cmis.test.General.TestCmisGetBasicTypes;
import de.cmis.test.General.TestCmisGetIdOfRootFolder;
import de.cmis.test.General.TestCmisGetRepositories;
import de.cmis.test.General.TestCmisGetSession;

/**
 * Ein CMIS-Testprogramm.
 * Hiermit können Grundfunktionen von Repositories
 *  mit CMIS-Anbindung im Atompub-Format (CMIS 1.0, CMIS 1.1)
 *  und Browser-Format (CMIS 1.1) getestet werden.
 *  Die Default-Einstellungen für Benutzer und Repositories können geändert werden.
 *
 * @author Thorsten Diederichs
 * @version 1.0
 */

public class AllTestsMain {
	
	/**
     * Hauptprogramm.
     *
     * Hier werden alle Tests in sinnvoller Reihenfolge durchlaufen
     * und die Ausgaben in eine H2-Datenbank geschrieben. 
     */

	public static void main(String[] args) throws IOException {
		
		// Hibernate Session für Logging erzeugen
		Tool testSession = new Tool();
		testSession.openSession();		

		// Allgemeine Tests		
		GetAllRepositoriesAllBindings.go();
		
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
