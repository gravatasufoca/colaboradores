package com.gravatasufoca.controller;

import com.gravatasufoca.session.LoginSession;
import com.gravatasufoca.session.UsuarioAutenticado;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * criado por bruno em 07/09/17.
 */
@Path("/oauth")
public class LoginController extends ControllerHelper {

    private final String CLIENTEID = "clienteTeste";
    private final String CLIENTESECRET = "secret";

    @Inject
    private LoginSession loginSession;

    @Inject
    private UsuarioAutenticado usuarioAutenticado;

    @POST
    @Path("login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response authorize(@Context HttpServletRequest request)
                    throws OAuthSystemException {
        try {
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

            if (!CLIENTEID.equals(oauthRequest.getClientId())) {
                return mensagemInvalido("ClienteID invalido.");
            }

            if (!CLIENTESECRET.equals(oauthRequest.getClientSecret())) {
                return mensagemInvalido("client_secret invalido");
            }

            if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                            .equals(GrantType.PASSWORD.toString())) {
                if (!checkUsuario(oauthRequest.getUsername(),
                                oauthRequest.getPassword())) {
                    return mensagemInvalido("Usuário ou senha inválido");
                }
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                            .equals(GrantType.REFRESH_TOKEN.toString())) {
                return mensagemInvalido("método de autenticação inválido");
            }

            final String accessToken = oauthIssuerImpl.accessToken();
            //            database.addToken(accessToken);

            HttpSession session = request.getSession();
            session.setAttribute("token", accessToken);

            loginSession.putToken(accessToken);

            usuarioAutenticado.setAutenticado(true);
            usuarioAutenticado.setToken(accessToken);
            usuarioAutenticado.setHttpSession(session);

            OAuthResponse response = OAuthASResponse
                            .tokenResponse(HttpServletResponse.SC_OK)
                            .setAccessToken(accessToken)
                            .setExpiresIn("3600")
                            .buildJSONMessage();
            return Response.status(response.getResponseStatus())
                            .entity(response.getBody()).build();

        } catch (OAuthProblemException e) {
            OAuthResponse res = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .error(e)
                            .buildJSONMessage();
            return Response
                            .status(res.getResponseStatus()).entity(res.getBody())
                            .build();
        }
    }

    @GET
    @Path("token")
    @Produces("application/json")
    public Response getToken(@Context HttpServletRequest request)
                    throws OAuthSystemException {
        try {
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.HEADER);
            String accessToken = oauthRequest.getAccessToken();

            // Validate the access token
            if (!tokeValido(accessToken, request)) {
                // Return the OAuth error message
                OAuthResponse oauthResponse = OAuthRSResponse
                                .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                                .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                                .buildHeaderMessage();

                return Response.status(Response.Status.UNAUTHORIZED)
                                .header(OAuth.HeaderType.WWW_AUTHENTICATE,
                                                oauthResponse
                                                                .getHeader(OAuth.HeaderType.WWW_AUTHENTICATE))
                                .build();

            }

            OAuthResponse response = OAuthASResponse
                            .tokenResponse(HttpServletResponse.SC_OK)
                            .setAccessToken(accessToken)
                            .setParam("usuario", "Nome usuario")
                            .buildJSONMessage();
            return Response.status(response.getResponseStatus())
                            .entity(response.getBody()).build();
        } catch (OAuthProblemException e) {
            OAuthResponse res = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .error(e)
                            .buildJSONMessage();
            return Response
                            .status(res.getResponseStatus()).entity(res.getBody())
                            .build();
        }
    }

    @GET
    @Path("logout")
    @Produces("application/json")
    public Response logout(@Context HttpServletRequest request)
                    throws OAuthSystemException, OAuthProblemException {

        if (request.getSession(false) != null) {
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.HEADER);
            loginSession.removeToken(oauthRequest.getAccessToken());
            usuarioAutenticado.setAutenticado(false);
            request.getSession().invalidate();
        }
        return Response.status(Response.Status.OK).build();
    }

    private boolean tokeValido(String accessToken, HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return false;
        }
        return request.getSession().getAttribute("token").equals(accessToken);
    }

    private boolean checkUsuario(String username, String password) {
        return true;
    }

    private Response mensagemInvalido(String msg) {
        return criaMensagemResposta(msg, null, null);
    }
}
