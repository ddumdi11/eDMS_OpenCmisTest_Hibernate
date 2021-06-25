package de.cmis.test;

import java.io.IOException;

import de.cmis.test.Documents.CheckVersioning;
import de.cmis.test.Folders.CreateFolder;
import de.cmis.test.Folders.DeleteFolder;
import de.cmis.test.Folders.GetFolderHirarchyFromRootFolderOfRepository;
import de.cmis.test.Folders.TraversThroughRootFolderHirarchy;
import de.cmis.test.General.GetAclOfRootFolder;
import de.cmis.test.General.GetAllPropertyTypes;
import de.cmis.test.General.GetAllRepositoriesByAllBindings;
import de.cmis.test.General.GetBasicTypesOfCmisSpecification;
import de.cmis.test.General.GetRootFolderId;
import de.cmis.test.General.GetSessionFromRepositoryEndpoint;
import de.cmis.test.General.GetSessionToRepository;
import de.cmis.test.Properties.GetBasicRepositoryInfo;
import de.cmis.test.Properties.GetPropertiesOfFolderType;
import de.cmis.test.Properties.GetPropertiesOfRootFolder;

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
		
		// Testeinstellungen festlegen
		TestSetting setting = new TestSetting().getInstance();
		
		// Hibernate Session für Logging erzeugen
		Tool testSession = new Tool();
		testSession.openSession();		

		// Allgemeine Tests
		// Berücksichtigt nicht die Testeinstellung
		GetAllRepositoriesByAllBindings.go();
		GetSessionToRepository.go();
		GetSessionFromRepositoryEndpoint.go();		
		// Ab hier mit Testeinstellung "setting"
		GetBasicTypesOfCmisSpecification.go(setting);
		GetRootFolderId.go(setting);
		GetAclOfRootFolder.go(setting);
		GetAllPropertyTypes.go(setting);
		
		// Eigenschaften (Properties) Tests
		GetPropertiesOfRootFolder.go(setting);
		GetPropertiesOfFolderType.go(setting);
		GetBasicRepositoryInfo.go(setting);
		
		// Order (Folder) Tests
		TraversThroughRootFolderHirarchy.go(); // Ohne Settings
		CreateFolder.go(setting);
		DeleteFolder.go(setting);
		GetFolderHirarchyFromRootFolderOfRepository.go(setting);
		
		// Dokument Tests

		CheckVersioning.go(setting.getServerDefaultSetting("Alfresco"));

		
		
		
		
				
		
		
		
		
		// Hibernate Session für Logging schließen
		testSession.closeSession();
		

	}	

}
