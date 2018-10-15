package test.app.myapp.net.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.app.myapp.BuildConfig;
import test.app.myapp.net.AlbumApi;

/**
 * Provides network related dependencies.
 */
@Module
class NetModule {
    private static final String baseURL = "https://jsonplaceholder.typicode.com/";

    @Singleton
    @Provides
    public AlbumApi getAlbumApi(OkHttpClient okHttpClient) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(AlbumApi.class);
    }

    @Singleton
    @Provides
    public OkHttpClient getHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(loggingInterceptor);
        return okHttpClientBuilder.build();

    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return loggingInterceptor;
    }

}
