package com.myapp.api;

import com.myapp.model.Album;
import com.myapp.model.Photo;
import com.myapp.model.User;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TypicodeApi {

    @GET("/users")
    Observable<List<User>> getUsers();

    @GET("/users/{userId}/albums")
    Observable<List<Album>> getAlbums(@Path("userId") int userId);

    @GET("/albums/{albumId}/photos")
    Observable<List<Photo>> getPhotos(@Path("albumId") int userId);
}
