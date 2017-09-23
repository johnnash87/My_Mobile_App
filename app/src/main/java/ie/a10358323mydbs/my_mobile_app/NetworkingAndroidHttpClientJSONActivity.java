package ie.a10358323mydbs.my_mobile_app;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkingAndroidHttpClientJSONActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask().execute();
		final ListView view = getListView();
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

		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			List<String> result = new ArrayList<String>();
			String JSONResponse = new BasicResponseHandler()
					.handleResponse(response);
			try {
				JSONArray stations = (JSONArray)new JSONTokener(JSONResponse).nextValue();
				for (int idx = 0; idx < stations.length(); idx++) {

					JSONObject station = (JSONObject) stations.get(idx);
					result.add("Bike stand name:" + station.get(NAME_TAG) + "\nNumber:" + station.get(NUMBER_TAG) +"\nPosition:" + station.get(POSITION_TAG));

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}