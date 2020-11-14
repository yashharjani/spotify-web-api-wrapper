package spotify.api.authorization;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import spotify.api.enums.GrantType;
import spotify.config.ApiUrl;
import spotify.exceptions.HttpRequestFailedException;
import spotify.exceptions.SpotifyAuthorizationFailedException;
import spotify.factories.RetrofitClientFactory;
import spotify.models.authorization.ClientCredentialsFlowTokenResponse;
import spotify.retrofit.services.ClientCredentialsFlowService;

import java.io.IOException;

public class ClientCredentialsFlow {
    private final Retrofit httpClient;
    private ClientCredentialsFlowTokenResponse clientCredentialsFlowTokenResponse;

    public ClientCredentialsFlow() {
        httpClient = RetrofitClientFactory.getRetrofitClient(ApiUrl.ACCOUNTS_URL_HTTPS);
    }

    public void doClientCredentialsFlow(String clientId, String clientSecret) {
        ClientCredentialsFlowService clientCredentialsFlowService = httpClient.create(ClientCredentialsFlowService.class);
        final String base64EncodedBasicAuth = Credentials.basic(clientId, clientSecret);

        Call<ClientCredentialsFlowTokenResponse> call = clientCredentialsFlowService.getToken(base64EncodedBasicAuth, GrantType.CLIENT_CREDENTIALS);

        try {
            Response<ClientCredentialsFlowTokenResponse> response = call.execute();

            if (response.body() == null) {
                throw new SpotifyAuthorizationFailedException("Authorizing with the given credentials has failed!");
            }

            this.clientCredentialsFlowTokenResponse = response.body();
        } catch (IOException e) {
            throw new HttpRequestFailedException(e.getMessage());
        }
    }

    public ClientCredentialsFlowTokenResponse getClientCredentialsFlowToken() {
        return clientCredentialsFlowTokenResponse;
    }
}
