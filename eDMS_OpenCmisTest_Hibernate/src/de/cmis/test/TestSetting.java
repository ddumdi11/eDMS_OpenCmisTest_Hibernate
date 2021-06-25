package de.cmis.test;

/**
 * Testeinstellungen (Singleton) Setzen der Testeinstellungen durch Benutzer.
 * Abfrage mithilfe von JOptionPane.
 * 
 * @author Diederichs
 *
 */
public class TestSetting {

	private static TestSetting instance; // vor Zugriff von außen geschützt und statisch
	private static String serverName;
	private static String bindingType;
	private static String bindingUrl;
	private static String repositoryId;
	private static String userName;
	private static String userPwd;

	private static final String OCS_CONST_BINDING = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/";
	private static final String ALF_CONST_BINDING = "http://localhost:8080/alfresco/api/-default-/cmis/versions/";

	TestSetting() {
	} // privater Konstruktor mit Zugriffsschutz von außen

	public static TestSetting getInstance() { // öffentliche Methode, Aufruf durch Code
		if (instance == null) { // nur wenn keine Instanz besteht, dann erstelle eine neue
			instance = new TestSetting();
			TestSetting.init();
		}
		return instance;
	}

	/**
	 * Default-Einstellung
	 */
	public static void init() {
		serverName = "OpenCmisServer";
		bindingType = "atom11";
		repositoryId = "A1";
		initBindingUrl(serverName);
		initUserPwd(serverName);
	}

	public static void initBindingUrl(String serverName) {
		if (serverName.equals("Alfresco")) {
			if (bindingType.equals("atom")) {
				bindingUrl = ALF_CONST_BINDING + "1.0/atom";
			} else if (bindingType.equals("atom11")) {
				bindingUrl = ALF_CONST_BINDING + "1.1/atom";
			}
		} else if (serverName.equals("OpenCmisServer")) {
			bindingUrl = OCS_CONST_BINDING + bindingType;
		}
	}

	public static void initUserPwd(String serverName) {
		if (serverName.equals("Alfresco")) {
			userName = "admin";
			userPwd = "Lanuv1111";
		} else if (serverName.equals("OpenCmisServer")) {
			userName = "";
			userPwd = "";
		}
	}

	public static void setTestSetting() {

	}

	public static String[] getTestSetting() {
		String[] setting = null;

		return setting;
	}

	public String getServerName() {
		return serverName;
	}

	public String getBindingType() {
		return bindingType;
	}
	
	public String getBindingUrl() {
		return bindingUrl;
	}
	
	public String getRepositoryID() {
		return repositoryId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserPwd() {
		return userPwd;
	}

}
