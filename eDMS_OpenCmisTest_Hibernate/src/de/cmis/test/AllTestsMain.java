package de.cmis.test;

import java.io.IOException;

import de.cmis.test.ACL.AddAceToAclOfObject;
import de.cmis.test.ACL.CheckAclSupport;
import de.cmis.test.ACL.GetAclCapabilities;
import de.cmis.test.ACL.GetAclsAssociatedWithObject;
import de.cmis.test.ACL.MapAclsToAllowableActions;
import de.cmis.test.ChangeLogSupport.CheckChangeLogSupport;
import de.cmis.test.Documents.Append2ContentStreamOfDocument;
import de.cmis.test.Documents.CheckUnfilingSupport;
import de.cmis.test.Documents.ContentStreamCRUDCapabilities;
import de.cmis.test.Documents.CreateDocumentWithContentInTestFolder;
import de.cmis.test.Documents.CreateEmptyDocumentInTestFolder;
import de.cmis.test.Documents.CreateUnfiledDocumentInRootFolder;
import de.cmis.test.Documents.DeleteContentStreamOfDocument;
import de.cmis.test.Documents.DeleteDocument;
import de.cmis.test.Documents.GetContentStreamOfDocument;
import de.cmis.test.Documents.GetContentsOfFirstDocumentInRootFolder;
import de.cmis.test.Documents.GetImportantPropertyValuesFromFirstDocumentInRootFolder;
import de.cmis.test.Documents.GetMimeTypeOfDocument;
import de.cmis.test.Documents.GetPropertiesOfFirstDocumentInRootFolder;
import de.cmis.test.Documents.RenameDocumentInTestFolder;
import de.cmis.test.Documents.SetContentStreamOfDocument;
import de.cmis.test.Documents.UpdateContentOfDocument;
import de.cmis.test.Folders.CreateFolder;
import de.cmis.test.Folders.DeleteFolder;
import de.cmis.test.Folders.GetFolderByPath;
import de.cmis.test.Folders.GetFolderHirarchyFromRootFolderOfRepository;
import de.cmis.test.Folders.GetParentsOfFolder;
import de.cmis.test.Folders.TraversThroughRootFolderHirarchy;
import de.cmis.test.General.GetAclOfRootFolder;
import de.cmis.test.General.GetAllPropertyTypes;
import de.cmis.test.General.GetAllRepositoriesByAllBindings;
import de.cmis.test.General.GetBasicTypesOfCmisSpecification;
import de.cmis.test.General.GetRootFolderId;
import de.cmis.test.General.GetSessionFromRepositoryEndpoint;
import de.cmis.test.General.GetSessionToRepository;
import de.cmis.test.ItemsRelationshipsPolicies.ApplyPolicyToObject;
import de.cmis.test.ItemsRelationshipsPolicies.CheckAllowedSourceAndTargetTypes;
import de.cmis.test.ItemsRelationshipsPolicies.CheckIfRelationshipExists;
import de.cmis.test.ItemsRelationshipsPolicies.CheckItemTypeSupport;
import de.cmis.test.ItemsRelationshipsPolicies.CheckPolicyControllabilityOfObject;
import de.cmis.test.ItemsRelationshipsPolicies.CheckPolicySupport;
import de.cmis.test.ItemsRelationshipsPolicies.CheckRelationshipSupport;
import de.cmis.test.ItemsRelationshipsPolicies.CreateFiledPolicy;
import de.cmis.test.ItemsRelationshipsPolicies.CreateItemCustomType;
import de.cmis.test.ItemsRelationshipsPolicies.CreateItemFiled;
import de.cmis.test.ItemsRelationshipsPolicies.CreateItemUnfiled;
import de.cmis.test.ItemsRelationshipsPolicies.CreateRelationship;
import de.cmis.test.ItemsRelationshipsPolicies.CreateUnfiledPolicy;
import de.cmis.test.ItemsRelationshipsPolicies.GetPoliciesAppliedToObject;
import de.cmis.test.MetadataAndTypes.ApplyConstraintsOnProperty;
import de.cmis.test.MetadataAndTypes.GetCreatablePropertyTypes;
import de.cmis.test.MetadataAndTypes.GetMetadata1;
import de.cmis.test.MetadataAndTypes.GetMetadata2;
import de.cmis.test.MetadataAndTypes.GetPropertyDefinitionsFromProperty;
import de.cmis.test.MetadataAndTypes.GetTypeMutabilitySettings;
import de.cmis.test.MetadataAndTypes.GetTypeOfObject;
import de.cmis.test.MetadataAndTypes.GetTypeSettableAttributes;
import de.cmis.test.Properties.GetBasicRepositoryInfo;
import de.cmis.test.Properties.GetPropertiesOfFolderType;
import de.cmis.test.Properties.GetPropertiesOfRootFolder;
import de.cmis.test.Queries.ExecuteQueriesFromList;
import de.cmis.test.Queries.GetQuerySupport;
import de.cmis.test.Queries.QueryAllDocumentsOfRepository;
import de.cmis.test.Renditions.AccessContentAssociatedWithRendition1;
import de.cmis.test.Renditions.AccessContentAssociatedWithRendition2;
import de.cmis.test.Renditions.AccessContentAssociatedWithRendition3;
import de.cmis.test.Renditions.GetDocumentObjectTypeDefinitions;
import de.cmis.test.Renditions.GetRenditionAttributes1;
import de.cmis.test.Renditions.GetRenditionAttributes2;
import de.cmis.test.RepositoryCapabilities.CanSearchAllVersionsOfDocument;
import de.cmis.test.RepositoryCapabilities.CheckAclCapabilities;
import de.cmis.test.RepositoryCapabilities.CheckAllowablePropertyTypesWhileCreationOrUpdatingObjectTypeDefinitions;
import de.cmis.test.RepositoryCapabilities.CheckContentStreamUpdationCapability;
import de.cmis.test.RepositoryCapabilities.CheckGetDescendantsSupport;
import de.cmis.test.RepositoryCapabilities.CheckGetFolderTreeSupport;
import de.cmis.test.RepositoryCapabilities.CheckMultiFilingCapability;
import de.cmis.test.RepositoryCapabilities.CheckOrderByCapabilitySupport;
import de.cmis.test.RepositoryCapabilities.CheckRenditionSupport;
import de.cmis.test.RepositoryCapabilities.CheckSettableObjectTypeAttributesWhileCreationNewObjectType;
import de.cmis.test.RepositoryCapabilities.CheckUnfilingCapability;
import de.cmis.test.RepositoryCapabilities.CheckVersioningCapabilities;
import de.cmis.test.RepositoryCapabilities.GetQueryCapabilitiesOfRepository;
import de.cmis.test.RepositoryCapabilities.GetQueryJoiningCapabilities;
import de.cmis.test.RepositoryCapabilities.GetVendorNameProductNameProductVersionOfRepository;
import de.cmis.test.RepositoryCapabilities.IsPrivateWorkingCopySearchable;
import de.cmis.test.RepositoryCapabilities.IsPrivateWorkingCopyUpdatable;
import de.cmis.test.SecondaryTypes.RemoveSecondaryType;
import de.cmis.test.SecondaryTypes.SetSecondaryType;
import de.cmis.test.SecondaryTypes.SetSecondaryType2;

/**
 * Ein CMIS-Testprogramm. Hiermit können Grundfunktionen von Repositories mit
 * CMIS-Anbindung im Atompub-Format (CMIS 1.0, CMIS 1.1) und Browser-Format
 * (CMIS 1.1) getestet werden. Die Default-Einstellungen für Benutzer und
 * Repositories können geändert werden.
 *
 * @author Thorsten Diederichs
 * @version 1.0
 */

public class AllTestsMain {

	/**
	 * Hauptprogramm.
	 *
	 * Hier werden alle Tests in sinnvoller Reihenfolge durchlaufen und die Ausgaben
	 * in eine H2-Datenbank geschrieben.
	 * 
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws IOException, InterruptedException {

		// Testeinstellungen festlegen
		TestSetting setting = new TestSetting().getInstance();

		// Hibernate Session für Logging erzeugen
		Tool testSession = new Tool();
		testSession.openSession();

		// Bereinigen der Server um durch Testdurchläufe angelegte Objekte
		//ResetAlfresco.go(setting.getServerDefaultSetting("Alfresco"));
		ResetOpenCmisServer.go(setting);
		//System.exit(0);

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
		//System.exit(0);

		// Eigenschaften (Properties) Tests
		GetPropertiesOfRootFolder.go(setting);
		GetPropertiesOfFolderType.go(setting);
		GetBasicRepositoryInfo.go(setting);
		//System.exit(0);

		// Order (Folder) Tests
		TraversThroughRootFolderHirarchy.go(); // Ohne Setting
		CreateFolder.go(setting);
		DeleteFolder.go(setting);
		GetFolderHirarchyFromRootFolderOfRepository.go(setting);
		GetFolderByPath.go(setting);
		GetParentsOfFolder.go(setting);
		//System.exit(0);

		// Dokument Tests
		GetPropertiesOfFirstDocumentInRootFolder.go(); // Ohne Setting
		GetContentsOfFirstDocumentInRootFolder.go(); // Ohne Setting
		GetImportantPropertyValuesFromFirstDocumentInRootFolder.go(setting);
		CreateEmptyDocumentInTestFolder.go(setting);
		CreateDocumentWithContentInTestFolder.go(setting);
		CheckUnfilingSupport.go(setting);
		CreateUnfiledDocumentInRootFolder.go(setting);
		RenameDocumentInTestFolder.go(setting);
		UpdateContentOfDocument.go(setting);
		//CheckVersioning.go(setting.getServerDefaultSetting("Alfresco"));
		DeleteDocument.go(setting);
		ContentStreamCRUDCapabilities.go(setting);
		SetContentStreamOfDocument.go(setting);
		GetContentStreamOfDocument.go(setting);
		Append2ContentStreamOfDocument.go(setting);
		DeleteContentStreamOfDocument.go(setting);
		GetMimeTypeOfDocument.go(setting);
		//System.exit(0);

		// Wiedergaben (Renditions) Tests
		GetRenditionAttributes1.go(setting);
		GetRenditionAttributes2.go(setting);
		AccessContentAssociatedWithRendition1.go(setting);
		AccessContentAssociatedWithRendition2.go(setting);
		AccessContentAssociatedWithRendition3.go(setting);
		GetDocumentObjectTypeDefinitions.go(setting);
		//System.exit(0);

		// Artikel/Gegenstände/Positionen (Items), Beziehungen (Relationships),
		// Richtlinien (Policies)		
		CheckItemTypeSupport.go(setting);
		CreateItemUnfiled.go(setting);
		CreateItemFiled.go(setting);
		CreateItemCustomType.go(setting);
		CheckRelationshipSupport.go(setting);
		CheckIfRelationshipExists.go(setting);
		CreateRelationship.go(setting);
		CheckAllowedSourceAndTargetTypes.go(setting);
		CheckPolicySupport.go(setting);
		CheckPolicyControllabilityOfObject.go(setting);
		CreateUnfiledPolicy.go(setting);
		CreateFiledPolicy.go(setting);
		ApplyPolicyToObject.go(setting);
		GetPoliciesAppliedToObject.go(setting);
		//System.exit(0);

		// Sekundäre Typen (Secondary Types) Tests
		SetSecondaryType.go(setting);
		SetSecondaryType2.go(setting);
		RemoveSecondaryType.go(setting);
		//System.exit(0);

		// Metadaten (Metadata) und Typen (Types)
		GetMetadata1.go(setting);
		GetMetadata2.go(setting);
		GetTypeOfObject.go(setting);
		GetPropertyDefinitionsFromProperty.go(setting);
		ApplyConstraintsOnProperty.go(setting);
		GetTypeMutabilitySettings.go(setting);
		GetTypeSettableAttributes.go(setting);
		GetCreatablePropertyTypes.go(setting);
		//System.exit(0);

		// Zugriffskontrolle (ACL - AccessControl)
		CheckAclSupport.go(setting);
		GetAclCapabilities.go(setting);
		GetAclsAssociatedWithObject.go(setting);
		MapAclsToAllowableActions.go(setting);
		AddAceToAclOfObject.go(setting);
		//System.exit(0);

		// Abfragen (Queries)
		GetQuerySupport.go(setting);
		QueryAllDocumentsOfRepository.go(setting);
		ExecuteQueriesFromList.go(setting);
		//System.exit(0);
		
		// Repository-Fähigkeiten/Funktionen (Repository Capabilities)
		// Navigationsfähigkeiten/-funktionen (Navigation Capabilities)
		CheckGetDescendantsSupport.go(setting);
		CheckGetFolderTreeSupport.go(setting);
		CheckOrderByCapabilitySupport.go(setting);
		// Objektfähigkeiten/-funktionen
		CheckContentStreamUpdationCapability.go(setting);
		CheckChangeLogSupport.go(setting);
		CheckRenditionSupport.go(setting);
		// Ablagefähigkeiten/-funktionen
		CheckMultiFilingCapability.go(setting);
		CheckUnfilingCapability.go(setting);
		// Versionierungsfähigkeiten/-funktionen
		CheckVersioningCapabilities.go(setting);
		IsPrivateWorkingCopyUpdatable.go(setting);
		IsPrivateWorkingCopySearchable.go(setting);
		CanSearchAllVersionsOfDocument.go(setting);
		// Abfragefähigkeiten/-funktionen
		GetQueryCapabilitiesOfRepository.go(setting);
		GetQueryJoiningCapabilities.go(setting);
		// Typfähigkeiten/-funktionen
		CheckAllowablePropertyTypesWhileCreationOrUpdatingObjectTypeDefinitions.go(setting);
		CheckSettableObjectTypeAttributesWhileCreationNewObjectType.go(setting);
		// ACL (AccessControlList) Fähigkeiten/Funktionen
		CheckAclCapabilities.go(setting);
		// Allgemein
		GetVendorNameProductNameProductVersionOfRepository.go(setting);
				
		
		System.exit(0);
		

		// Hibernate Session für Logging schließen
		testSession.closeSession();

	}

}
