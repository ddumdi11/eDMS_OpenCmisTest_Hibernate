package de.cmis.test;

import de.cmis.test.Session.SessionSingleton;

/**
 * Testeinstellungen (Singleton)
 * Setzen der Testeinstellungen durch Benutzer.
 * Abfrage mithilfe von JOptionPane.
 * 
 * @author Diederichs
 *
 */
public class TestSetting {

	private static TestSetting instance; // vor Zugriff von außen geschützt und statisch
	private String serverName;
	private String bindingType;
	private String repositoryId;
	private String userName;
	private String userPwd;
	
	private TestSetting() {
	} // privater Konstruktor mit Zugriffsschutz von außen
	
	public static TestSetting getInstance() { // öffentliche Methode, Aufruf durch Code
		if (instance == null) { // nur wenn keine Instanz besteht, dann erstelle eine neue
			instance = new TestSetting();
		}
		return instance;
	}
	
	public static void setTestSetting() {
		
	}
	
	public static String[] getTestSetting() {
		String[] setting = null;
		
		return setting;
	}
	
}
