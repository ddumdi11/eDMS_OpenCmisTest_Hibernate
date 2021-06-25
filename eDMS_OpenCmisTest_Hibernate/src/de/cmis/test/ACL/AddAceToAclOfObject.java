package de.cmis.test.ACL;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.Ace;
import org.apache.chemistry.opencmis.commons.data.Acl;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class AddAceToAclOfObject {

	public static OperationContext getDefaultContext() {
		OperationContextImpl operationalContext = new OperationContextImpl();

		Set<String> filterSet = new HashSet<String>();

		operationalContext.setIncludeAcls(true);
		operationalContext.setIncludeAllowableActions(true);
		operationalContext.setIncludePolicies(false);

		filterSet.add(PropertyIds.VERSION_SERIES_ID);
		filterSet.add(PropertyIds.VERSION_SERIES_CHECKED_OUT_ID);
		filterSet.add(PropertyIds.IS_VERSION_SERIES_CHECKED_OUT);
		filterSet.add(PropertyIds.IS_PRIVATE_WORKING_COPY);
		filterSet.add(PropertyIds.IS_LATEST_VERSION);
		filterSet.add(PropertyIds.OBJECT_ID);
		filterSet.add(PropertyIds.NAME);
		filterSet.add(PropertyIds.CONTENT_STREAM_LENGTH);
		filterSet.add(PropertyIds.CHANGE_TOKEN);
		filterSet.add(PropertyIds.LAST_MODIFICATION_DATE);
		filterSet.add(PropertyIds.LAST_MODIFIED_BY);
		filterSet.add(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		filterSet.add(PropertyIds.CONTENT_STREAM_MIME_TYPE);

		operationalContext.setFilter(filterSet);

		return operationalContext;
	}

	private static void printAce(Ace ace) {
		List<String> permissions = ace.getPermissions();
		org.apache.chemistry.opencmis.commons.data.Principal principal = ace.getPrincipal();

		Tool.printAndLog("User '" + principal.getId() + "' has below permissions");

		for (String perm : permissions) {
			Tool.printAndLog(perm);
		}
	}

	private static void printACL(Acl acl) {
		List<Ace> aces = acl.getAces();

		for (Ace ace : aces) {
			printAce(ace);
		}

	}

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");
		session.setDefaultContext(getDefaultContext());

		/* Get the object by path */
		CmisObject cmisObject = session.getObjectByPath("/My_Folder-0-0");

		/* Get ACL associated with cmis object */
		Acl acl = cmisObject.getAcl();
		List<Ace> aces = acl.getAces();

		/* Create new ace */
		Ace aceToAdd = session.getObjectFactory().createAce("chamu", Collections.singletonList("cmis:read"));

		/* Add ace to aces */
		aces.add(aceToAdd);

		/* Set the updated aces */
		cmisObject.setAcl(aces);

		cmisObject.refresh();

		acl = cmisObject.getAcl();
		printACL(acl);
	}
}