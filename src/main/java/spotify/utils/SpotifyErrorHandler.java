package spotify.utils;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import spotify.exceptions.SpotifyActionFailedException;
import spotify.models.errors.SpotifyError;

public class SpotifyErrorHandler {
    private static final Logger logger = LoggerUtils.getLogger(ResponseChecker.class);

    public static void handleErrorBody(ResponseBody errorBody) {
        if (errorBody == null) return;

        Gson gson = new Gson();
        SpotifyError spotifyError = gson.fromJson(errorBody.charStream(), SpotifyError.class);

        if (spotifyError == null) {
            throw new SpotifyActionFailedException("Failed to parse Spotify error.");
        }

        final int statusCode = spotifyError.getError().getStatus();
        final String message = spotifyError.getError().getMessage();

        if (statusCode > 300) {
            throw new SpotifyActionFailedException(message);
        }
    }
}
