package test.app.myapp.net.di;

import javax.inject.Singleton;

import dagger.Component;
import test.app.myapp.albums.AlbumRepo;


@Singleton
@Component(modules = {NetModule.class})
public interface RepoComponent {
    void inject(AlbumRepo albumRepo);
}
