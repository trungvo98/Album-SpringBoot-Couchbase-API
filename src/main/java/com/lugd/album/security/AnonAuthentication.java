package com.lugd.album.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AnonAuthentication extends AbstractAuthenticationToken {

	private boolean isAuthenticated = true;
	
    public AnonAuthentication(boolean val) {
        super( null );
        this.isAuthenticated = val;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
    	//check token neu ko ton tai return false;
    	super.setAuthenticated(this.isAuthenticated);
    	return this.isAuthenticated;
        //return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        return true;
    }


}
