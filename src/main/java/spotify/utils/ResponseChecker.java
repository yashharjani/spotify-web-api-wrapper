package spotify.utils;


import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import spotify.api.enums.HttpStatusCode;
import spotify.exceptions.SpotifyActionFailedException;
import spotify.models.errors.SpotifyError;

public class ResponseChecker {

    public static <T> void throwIfRequestHasNotBeenFulfilledCorrectly(final Response<T> response, final HttpStatusCode expectedStatusCode) {
        HttpStatusValidator.validate(response, expectedStatusCode.toInt());
        SpotifyErrorHandler.handleErrorBody(response.errorBody());
    }

}