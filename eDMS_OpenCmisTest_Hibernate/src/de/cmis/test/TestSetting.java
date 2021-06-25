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
	private static final String ALF_CONST_BINDING = "http://localhost:8080/alfresco/api/-default-/public/cmis/versions/";


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
	
	public static String[] getServerDefaultSetting (String serverName) {
		String[] defaultSetting = new String[4];
		defaultSetting[0] = bindingType;
		if (serverName.equals("OpenCmisServer")) {			
			defaultSetting[1] = OCS_CONST_BINDING + bindingType;
			defaultSetting[2] = "";
			defaultSetting[3] = "";
		} else if (serverName.equals("Alfresco")) {
			if (bindingType.equals("atom")) {
				defaultSetting[1] = ALF_CONST_BINDING + "1.0/atom";
			} else if (bindingType.equals("atom11")) {
				defaultSetting[1] = ALF_CONST_BINDING + "1.1/atom";
			}
			defaultSetting[2] = "admin";
			defaultSetting[3] = "Lanuv1111";
		}		
		return defaultSetting;
	}

}
