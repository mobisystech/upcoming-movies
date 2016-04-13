package com.mobisys.android.upcoming_movies.assignment.rest.api;
import com.mobisys.android.upcoming_movies.assignment.model.ImageResponse;
import com.mobisys.android.upcoming_movies.assignment.model.MovieResponse;
import com.mobisys.android.upcoming_movies.assignment.model.Movie;
import com.mobisys.android.upcoming_movies.assignment.rest.RestCallback;
import retrofit.http.GET;


import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by mahavir on 10/6/15.
 */
public interface AssignmentApi {

    @GET("/movie/upcoming")
    public void upcomingMovies(@Query("api_key") String apikey, RestCallback<MovieResponse> restCallback);

    @GET("/movie/{id}")
    public void movieDetail(@Query("api_key") String apikey,@Path("id")String id,RestCallback<Movie> restCallback);

    @GET("/movie/{id}/images")
    public void getImages(@Query("api_key") String apikey,@Path("id")String id,RestCallback<ImageResponse> restCallback);
}
