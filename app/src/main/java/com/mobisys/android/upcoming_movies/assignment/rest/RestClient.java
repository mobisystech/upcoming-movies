package com.mobisys.android.upcoming_movies.assignment.rest;

import android.content.Context;


import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import com.mobisys.android.upcoming_movies.assignment.Constants;
import com.mobisys.android.upcoming_movies.assignment.rest.api.AssignmentApi;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.mime.TypedInput;

public class RestClient {

	public static String Google_Places_END_POINT = "https://api.themoviedb.org/3";
    private static AssignmentApi mGooglePlacesApi;

    public static class NetworkErrorException extends RuntimeException {
    }

    public static class CustomErrorHandler implements ErrorHandler {

        @Override
        public Throwable handleError( RetrofitError cause ) {
            if ( RetrofitError.Kind.NETWORK == cause.getKind() ) {
                Throwable throwable = cause.getCause();
                if ( throwable instanceof SocketTimeoutException)
                    return new NetworkErrorException();
                else if ( throwable instanceof ConnectException)
                    return new NetworkErrorException();
            }

            return cause;
        }
    }

    public static String getErrorFromResponse(RetrofitError error){
        String errorResponse = null;
        if (error!=null && error.getResponse()!=null){
            if (error.getResponse().getBody()!=null){
                TypedInput body = error.getResponse().getBody();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
                    StringBuilder out = new StringBuilder();
                    String newLine = System.getProperty("line.separator");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                        out.append(newLine);
                    }

                    errorResponse = out.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return errorResponse!=null?parseErrorResponse(errorResponse):"Unknown error occured!";
    }

    public static String parseErrorResponse(String errorResponse){
        try {
            JSONObject jsonObject = new JSONObject(errorResponse);
            JSONArray jsonArray = jsonObject.optJSONArray("errors");
            if (jsonArray!=null) return jsonArray.getString(0);

            String error = jsonObject.optString("errors");
            if (error!=null) return error;

            error = jsonObject.optString("error");
            return error;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private RestClient() {
	}

    public static AssignmentApi AssignmentApi(Context context) {
        if(mGooglePlacesApi ==null){
            setupGooglePlacesClient(context);
        }

        return mGooglePlacesApi;
    }

    private static void setupGooglePlacesClient(final Context context) {
		OkHttpClient okClient = new OkHttpClient();
		
		RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Google_Places_END_POINT)
                .setErrorHandler(new CustomErrorHandler())
                .setConverter(new JacksonConverter())
                .setClient(new OkClient(okClient))
                .setLogLevel(Constants.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);

		RestAdapter restAdapter = builder.build();
        mGooglePlacesApi = restAdapter.create(AssignmentApi.class);
    }
}
