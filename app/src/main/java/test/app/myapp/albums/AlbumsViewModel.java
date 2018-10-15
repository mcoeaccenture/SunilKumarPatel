package test.app.myapp.albums;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import test.app.myapp.data.Album;
import test.app.myapp.data.NetResponse;

public class AlbumsViewModel extends ViewModel {
    //private LiveData<NetResponse<List<Album>>> albumList;
    private final AlbumRepo albumRepo;

    public AlbumsViewModel() {
        albumRepo = AlbumRepo.getInstance();
    }

    public LiveData<NetResponse<List<Album>>> getAlbumList() {
        return albumRepo.getAlbumList();
    }
}
