package de.cmis.test;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.Action;

import de.cmis.test.Session.SessionSingleton;

public class CleanUpOpenCmisServer {

	public static void go() throws IOException, InterruptedException {

		// Am sinnvollsten ist der Restart des TomcatServers
		String tomcatPath2Bin = "/opt/tomcat9/bin";
		// Shutdown
		Runtime r = Runtime.getRuntime();
		Process p = r.exec("sudo sh " + tomcatPath2Bin + "/shutdown.sh");
		p.waitFor();
		// Neustart
		p = r.exec("sudo sh " + tomcatPath2Bin + "/startup.sh");
		p.waitFor();

	}

}
