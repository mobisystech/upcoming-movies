package assignment.mobisys.com.assignment.rest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

public abstract class RestCallback<T> implements Callback<T> {

	public abstract void failure(String restErrors, boolean networkError);
	
	@SuppressWarnings("deprecation")
	@Override
	public void failure(RetrofitError error) {
		String restErrors = null;
		boolean networkError = false;
		Throwable throwable = error.getCause();
		if ( throwable instanceof SocketTimeoutException || throwable instanceof  ConnectException || throwable instanceof RestClient.NetworkErrorException) {
            restErrors = "It seems you are not connected to internet or your data connection is slow or our server is down. Please try again later.";
            networkError = true;
        }  else if(error.getResponse()!=null&&error.getResponse().getBody()!=null){
    		String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
			if (json != null){
				try {
					JSONObject jsonObject = new JSONObject(json);
					JSONArray jsonArray = jsonObject.getJSONArray("errors");
					restErrors = jsonArray.getString(0);
				} catch (JSONException e) {
					restErrors = "Unknown error occured!";
					e.printStackTrace();
				}
			}
		}

        if(restErrors==null&&error!=null&&error.getCause()!=null) restErrors = error.getCause().getLocalizedMessage();
		failure(restErrors,networkError);
    }
}
