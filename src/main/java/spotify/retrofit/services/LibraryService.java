package spotify.retrofit.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

public interface LibraryService {
    @GET("me/albums/contains")
    Call<List<Boolean>> hasSavedAlbums(@Header("Authorization") String accessToken, @Query("ids") String albumIds);

    @GET("me/shows/contains")
    Call<List<Boolean>> hasSavedShows(@Header("Authorization") String accessToken, @Query("ids") String showIds);

    @GET("me/tracks/contains")
    Call<List<Boolean>> hasSavedTracks(@Header("Authorization") String accessToken, @Query("ids") String trackIds);
}
