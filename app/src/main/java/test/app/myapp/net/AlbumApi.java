package test.app.myapp.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import test.app.myapp.data.Album;

public interface AlbumApi {
    @GET("/albums")
    Call<List<Album>> getAlbums();
}
