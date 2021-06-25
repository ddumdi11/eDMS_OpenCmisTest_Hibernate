package de.cmis.test.General;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

/**
 * Repository-Ausgabe für Browser-Verbindung (nur in CMIS 1.1 implementiert).
 * 
 * @author Krishna / Thorsten Diederichs
 *
 */
public class BrowserBindingRepositoryUtil extends RepositoryUtil {

	/**
	 * Für diesen Test ist explizite Angabe der Verbindungs-Url und Benutzerdaten erforderlich.
	 * 
	 * @param userName
	 * @param password
	 * @param urlToConnect
	 */
	public BrowserBindingRepositoryUtil(String userName, String password, String urlToConnect) {
		super(userName, password, urlToConnect);
	}

	@Override
	public void populateProperties() {
		// user credentials
		parameters.put(SessionParameter.USER, this.getUserName());
		parameters.put(SessionParameter.PASSWORD, this.getPassword());

		// connection settings
		parameters.put(SessionParameter.ATOMPUB_URL, this.getUrlToConnect());
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

	}

}
