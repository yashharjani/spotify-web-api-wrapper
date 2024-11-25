package spotify.utils;

import org.slf4j.Logger;
import retrofit2.Response;

public class HttpStatusValidator {
    private static final Logger logger = LoggerUtils.getLogger(ResponseChecker.class);

    public static <T> void validate(Response<T> response, int expectedStatusCode) {
        final int actualHttpStatusCode = response.code();

        if (actualHttpStatusCode != expectedStatusCode) {
            logger.warn("HTTP request has not returned the expected status code. " +
                            "Expected: {}. Actual: {}.",
                    expectedStatusCode,
                    actualHttpStatusCode);
        }
    }
}
