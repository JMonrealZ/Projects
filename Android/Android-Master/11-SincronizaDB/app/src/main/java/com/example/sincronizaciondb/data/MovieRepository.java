package com.example.sincronizaciondb.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sincronizaciondb.app.MyApp;
import com.example.sincronizaciondb.data.local.MovieRoomDatabase;
import com.example.sincronizaciondb.data.local.dao.MovieDao;
import com.example.sincronizaciondb.data.local.entity.MovieEntity;
import com.example.sincronizaciondb.data.network.NetworkBoundResource;
import com.example.sincronizaciondb.data.network.Resource;
import com.example.sincronizaciondb.data.remote.ApiConstants;
import com.example.sincronizaciondb.data.remote.MovieApiService;
import com.example.sincronizaciondb.data.remote.RequestInterceptor;
import com.example.sincronizaciondb.data.remote.model.MoviesResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository(){
        // Local > Room
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_movies"
        ).build();
        movieDao = movieRoomDatabase.getMovieDao();

        //RequestInterceptor(para inyectar api_key)
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okHttpClientBuilder.build();

        // Remote > Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){
        //Tipo que devuelve room, tipo que devuelve la API
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                //Datos en room
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
//                return null;
                return  movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }
}
