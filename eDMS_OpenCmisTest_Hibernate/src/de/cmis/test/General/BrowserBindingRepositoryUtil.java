package de.cmis.test.General;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class BrowserBindingRepositoryUtil extends RepositoryUtilBrowser {

	public BrowserBindingRepositoryUtil(String userName, String password, String urlToConnect) {
		super(userName, password, urlToConnect);
	}

	@Override
	public void populateProperties() {
		super.populateProperties();
		// connection settings
		parameters.put(SessionParameter.BROWSER_URL, this.getUrlToConnect());
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
	}
}