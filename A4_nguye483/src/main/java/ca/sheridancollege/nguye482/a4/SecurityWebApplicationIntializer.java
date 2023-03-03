package ca.sheridancollege.nguye482.a4;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.nguye482.a4.security.SecurityConfig;

public class SecurityWebApplicationIntializer extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityWebApplicationIntializer() {
		super(SecurityConfig.class);
	}
}
