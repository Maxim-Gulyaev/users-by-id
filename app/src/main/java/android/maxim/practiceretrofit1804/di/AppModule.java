package android.maxim.practiceretrofit1804.di;

import android.maxim.practiceretrofit1804.model.Repository;
import android.maxim.practiceretrofit1804.model.UserAPI;
import android.maxim.practiceretrofit1804.model.UserResponse;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    String BASE_URL = "https://dummyjson.com/";

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public UserAPI provideUserAPI(Retrofit retrofit) {
        return retrofit.create(UserAPI.class);
    }

    @Singleton
    @Provides
    public MutableLiveData<UserResponse> provideMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Singleton
    @Provides
    public LiveData<UserResponse> provideLiveData(Repository repository) {
        return repository.getLiveData();
    }

    @Singleton
    @Provides
    public Repository provideRepository() {
        return new Repository();
    }
}
