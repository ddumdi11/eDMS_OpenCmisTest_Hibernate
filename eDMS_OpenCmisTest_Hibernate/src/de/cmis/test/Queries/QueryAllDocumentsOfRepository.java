package de.cmis.test.Queries;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.PropertyData;

import de.cmis.test.TestSetting;
import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class QueryAllDocumentsOfRepository {

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);

		String query = "SELECT * FROM cmis:document";

		ItemIterable<QueryResult> queryResults = session.query(query, true);

		for (QueryResult queryResult : queryResults) {
			List<PropertyData<?>> propertiesData = queryResult.getProperties();

			Tool.printAndLog("*********************************************************");
			for (PropertyData<?> propData : propertiesData) {
				Tool.printAndLog(propData.getId() + " : " + propData.getValues());
			}
			Tool.printAndLog("*********************************************************\n\n");
		}

	}
}