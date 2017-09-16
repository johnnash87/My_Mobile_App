package ie.a10358323mydbs.my_mobile_app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NetworkingAndroidHttpClientJSONActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask().execute();
	}

	private class HttpGetTask extends AsyncTask<Void, Void, List<String>> {

		private static final String URL = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=06ef3de9feffb007f9ab79f35d9787110fbb6826";

		AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

		@Override
		protected List<String> doInBackground(Void... params) {
			HttpGet request = new HttpGet(URL);
			JSONResponseHandler responseHandler = new JSONResponseHandler();
			try {
				return mClient.execute(request, responseHandler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			if (null != mClient)
				mClient.close();
			setListAdapter(new ArrayAdapter<String>(
					NetworkingAndroidHttpClientJSONActivity.this,
					R.layout.list_item, result));
		}
	}

	private class JSONResponseHandler implements ResponseHandler<List<String>> {

		private static final String NAME_TAG = "name";
		private static final String ADDRESS_TAG = "address";
		private static final String LATITUDE_TAG = "lat";
		private static final String POSITION_TAG = "position";
		private static final String LONGTITUDE_TAG = "lng";
		private static final String NUMBER_TAG = "number";
		// use map activity
		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			List<String> result = new ArrayList<String>();
			String JSONResponse = new BasicResponseHandler()
					.handleResponse(response);
			try {

				// Get top-level JSON Object - a Map
				//JSONObject responseObject = (JSONObject) new JSONTokener(
				//		JSONResponse).nextValue();

				// Extract value of "earthquakes" key -- a List
				JSONArray stations = (JSONArray)new JSONTokener(JSONResponse).nextValue();

				// Iterate over earthquakes list
				for (int idx = 0; idx < stations.length(); idx++) {

					// Get single earthquake data - a Map
					JSONObject station = (JSONObject) stations.get(idx);

					// Summarize earthquake data as a string and add it to
					// result
					result.add("Bike stand name:" + station.get(NAME_TAG) + "\nNumber:" + station.get(NUMBER_TAG) +"\nPosition:" + station.get(POSITION_TAG));
					//		+ NUMBER_TAG + " = "
					//		+ station.get(NUMBER_TAG) + "\n"
					//		+ NAME_TAG + " = "
					//		+ station.get(NAME_TAG) + "\n"
					//		+ ADDRESS_TAG + " = "
					//		+ station.getString(ADDRESS_TAG) + "\n"
					//+ LATITUDE_TAG + " = "
					//+ station.get(LATITUDE_TAG) + "\n"
					//+ LONGTITUDE_TAG + " was "
					//+ station.get(LONGTITUDE_TAG) + "\n"
					//      + "Source = " + station.getString("src"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}