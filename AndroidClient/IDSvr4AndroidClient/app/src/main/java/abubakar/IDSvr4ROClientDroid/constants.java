package abubakar.IDSvr4ROClientDroid;

import android.provider.SyncStateContract;

/**
 * Created by Abubakar on 23-Feb-16.
 */
public class constants {
    public static final String BaseAddress = "http://yourIpAddressOfYourDeployedAuthServer:22530/";

    public static final String AuthorizeEndpoint = BaseAddress + "/connect/authorize";
    public static final String LogoutEndpoint = BaseAddress + "/connect/endsession";
    public static final String TokenEndpoint = BaseAddress + "/connect/token";
    public static final String UserInfoEndpoint = BaseAddress + "/connect/userinfo";
    public static final String IdentityTokenValidationEndpoint = BaseAddress + "/connect/identitytokenvalidation";
    public static final String TokenRevocationEndpoint = BaseAddress + "/connect/revocation";
    public static final String IntrospectionEndpoint = BaseAddress + "/connect/introspect";

    public static final String AspNetWebApiSampleApi = "http://yourIpAddressOfYourDeployedSampleApi:3860/";
}
