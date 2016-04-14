package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateURL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;


public class URLValidator implements ConstraintValidator<ValidateURL, CharSequence> {
	private String protocol;
	private String host;
	private int port;

	public void initialize(ValidateURL validateUrl) {
		this.protocol = validateUrl.protocol();
		this.host = validateUrl.host();
		this.port = validateUrl.port();
	}

	public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
		if ( value == null || value.length() == 0 ) {
			return true;
		}

		java.net.URL url;
		try {
			url = new java.net.URL( value.toString() );
		}
		catch ( MalformedURLException e ) {
			return false;
		}

		if ( protocol != null && protocol.length() > 0 && !url.getProtocol().equals( protocol ) ) {
			return false;
		}

		if ( host != null && host.length() > 0 && !url.getHost().equals( host ) ) {
			return false;
		}

		if ( port != -1 && url.getPort() != port ) {
			return false;
		}

		return true;
	}
}
