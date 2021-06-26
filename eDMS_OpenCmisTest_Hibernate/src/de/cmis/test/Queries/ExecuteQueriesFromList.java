package de.cmis.test.Queries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.TestSetting;
import de.cmis.test.Session.SessionSingleton;

public class ExecuteQueriesFromList {

	private List<String> getListOfQueries() {
		List<String> listQueries = null;

		return listQueries;
	}

	public static void go(TestSetting setting) {
		Session session = SessionSingleton.getInstance().getSession(setting);
		
		try {

            File f = new File("./src/de/cmis/test/Files/QueryList.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading file using Buffered Reader");

            while ((readLine = b.readLine()) != null) {
                System.out.println(readLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
