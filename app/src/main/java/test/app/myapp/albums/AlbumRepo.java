package test.app.myapp.albums;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.app.myapp.data.Album;
import test.app.myapp.data.NetResponse;
import test.app.myapp.net.AlbumApi;
import test.app.myapp.net.di.DaggerRepoComponent;
import test.app.myapp.net.di.RepoComponent;

public class AlbumRepo {

    @SuppressWarnings("WeakerAccess")
    @Inject
    AlbumApi albumApi;

    private static AlbumRepo albumRepo;
    private final MutableLiveData<NetResponse<List<Album>>> albumList;

    private AlbumRepo() {
        albumList = new MutableLiveData<>();
        RepoComponent repoComponent = DaggerRepoComponent.builder()
                .build();
        repoComponent.inject(this);
    }

    public static AlbumRepo getInstance() {
        if (albumRepo == null) {
            synchronized (AlbumRepo.class) {
                albumRepo = new AlbumRepo();
            }
        }
        return albumRepo;
    }

    public LiveData<NetResponse<List<Album>>> getAlbumList() {
        if (albumList.getValue() == null ||
                (albumList.getValue().getData() == null && albumList.getValue().getStatus() != NetResponse.Status.PROGRESS)) {
            //Set initial value to data with the status.
            NetResponse<List<Album>> resource = new NetResponse<>(null, NetResponse.Status.PROGRESS, "");
            albumList.setValue(resource);
            //Fetch album list asynchronously with webAPI.
            albumApi.getAlbums().enqueue(new Callback<List<Album>>() {
                @Override
                public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                    NetResponse<List<Album>> resource = new NetResponse<>(response.body(), NetResponse.Status.SUCCESS, "");
                    albumList.setValue(resource);
                }

                @Override
                public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
                    NetResponse<List<Album>> response = new NetResponse<>(null, NetResponse.Status.ERROR, t.getMessage());
                    albumList.setValue(response);
                }
            });
        }
        return albumList;
    }
}
