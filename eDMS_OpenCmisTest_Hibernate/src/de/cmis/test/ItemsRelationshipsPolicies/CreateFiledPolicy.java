package de.cmis.test.ItemsRelationshipsPolicies;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.TestSetting;
import de.cmis.test.Session.SessionSingleton;

public class CreateFiledPolicy {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "a new unfiled policy");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "AuditPolicy");
		properties.put(PropertyIds.POLICY_TEXT, "my un policy description");

		Folder folder = (Folder) session.getObjectByPath("/My_Folder-0-0");

		ObjectId policyId = folder.createPolicy(properties);

		Policy policy = (Policy) session.getObject(policyId);

		System.out.println("Created By : " + policy.getCreatedBy());
		System.out.println("Description : " + policy.getDescription());
		System.out.println("Name : " + policy.getName());
		System.out.println("Policy Text : " + policy.getPolicyText());
		System.out.println("Policy Id : " + policy.getId());

	}

}