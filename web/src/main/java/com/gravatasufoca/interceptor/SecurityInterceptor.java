package com.gravatasufoca.interceptor;

import com.gravatasufoca.session.UsuarioAutenticado;
import org.apache.oltu.oauth2.common.OAuth;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * criado por bruno em 07/09/17.
 */
@Interceptor
@Autenticado
public class SecurityInterceptor implements Serializable {

    private static final long serialVersionUID = 8586360860143513542L;

    @Inject
    private UsuarioAutenticado usuarioAutenticado;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception{
        if(usuarioAutenticado.isAutenticado())
            return ctx.proceed();
        else
            return  Response.status(Response.Status.UNAUTHORIZED).build();

    }
}
