package com.gravatasufoca.session;

import javax.ejb.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * criado por bruno em 07/09/17.
 */
@Singleton
public class LoginSession {

    private Set<String> tokens=new HashSet<>();

    public LoginSession() {
    }

    public void putToken(String token){
        tokens.add(token);
    }

    public void removeToken(String token){
        tokens.remove(token);
    }

    public boolean isValid(String token){
        return tokens.contains(token);
    }
}
