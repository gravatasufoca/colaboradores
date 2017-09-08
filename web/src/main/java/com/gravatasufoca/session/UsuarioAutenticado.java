package com.gravatasufoca.session;

import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * criado por bruno em 07/09/17.
 */
@SessionScoped
public class UsuarioAutenticado implements Serializable{

    private static final long serialVersionUID = 1429669679222900932L;

    private String nome;
    private String token;
    private HttpSession httpSession;
    private boolean autenticado;



    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public UsuarioAutenticado() {
    }

}
