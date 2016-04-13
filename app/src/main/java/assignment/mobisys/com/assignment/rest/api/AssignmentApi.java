package assignment.mobisys.com.assignment.rest.api;
import assignment.mobisys.com.assignment.model.ImageResponse;
import assignment.mobisys.com.assignment.model.MovieResponse;
import assignment.mobisys.com.assignment.model.Result;
import assignment.mobisys.com.assignment.rest.RestCallback;
import retrofit.http.GET;







import org.json.JSONObject;

import java.util.Map;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by mahavir on 10/6/15.
 */
public interface AssignmentApi {

    @GET("/movie/upcoming")
    public void upcomingMovies(@Query("api_key") String apikey, RestCallback<MovieResponse> restCallback);

    @GET("/movie/{id}")
    public void movieDetail(@Query("api_key") String apikey,@Path("id")String id,RestCallback<Result> restCallback);

    @GET("/movie/{id}/images")
    public void getImages(@Query("api_key") String apikey,@Path("id")String id,RestCallback<ImageResponse> restCallback);
}
