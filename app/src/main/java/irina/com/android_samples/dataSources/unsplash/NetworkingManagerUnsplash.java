package irina.com.android_samples.dataSources.unsplash;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkingManagerUnsplash implements NetworkingManager {

    private static final String UNSPLASH_URL = "https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542";

    private List<PhotoItem> photoItems = new ArrayList<>();
    private int limit = 50;
    private int offset = 0;
    private boolean requestInProgress = false;

    @Override
    public void getImages(NetworkingManagerResult result) {
        getItems(result);
    }

    @Override
    public void fetchNewItemsFromPosition(int lastPosition, NetworkingManagerResult result) {
        //TODO: change URL
//        if (requestInProgress) {
//            return;
//        }
//        if (offset <= lastPosition) {
//            requestInProgress = true;
//            offset += limit;
//            getImages(result);
//        }
    }

    private void getItems(NetworkingManagerResult result) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(UNSPLASH_URL)
                .build();
        requestInProgress = true;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Gson gson = new Gson();
                String jsonData = response.body() != null ? response.body().string() : null;
                try {
                    JSONArray array = new JSONArray(jsonData);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);
                        PhotoItemUnsplash item = gson.fromJson(imgObject.toString(), PhotoItemUnsplash.class);
                        photoItems.add(item);
                    }
                } catch (JSONException e) {
                    Log.e("ERROR", e.getLocalizedMessage());
                } finally {
                    requestInProgress = false;
                    result.onGetItemsCompleteCallback(photoItems);
                }
            }
        });
    }

}
