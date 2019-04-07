package irina.com.android_samples.download.impl;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irina.com.android_samples.PhotoItem;
import irina.com.android_samples.download.NetworkManager;
import irina.com.android_samples.download.NetworkResultListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManagerImpl implements NetworkManager, Callback {

    private static final String PHOTO_TOP_10 = "https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542";

    private List<PhotoItem> photoItems = new ArrayList<>();

    NetworkResultListener resultListener;

    public NetworkManagerImpl(NetworkResultListener resultListener) {
        this.resultListener = resultListener;
    }

    @Override
    public void getGalleryItems() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(PHOTO_TOP_10)
                .build();
        client.newCall(request).enqueue(this);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final Gson gson = new Gson();
        String jsonData = response.body() != null ? response.body().string() : null;
        try {
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0; i < array.length(); i++) {
                JSONObject imgObject = array.getJSONObject(i);
                PhotoItem item = gson.fromJson(imgObject.toString(), PhotoItem.class);
                photoItems.add(item);
            }

            if (this.resultListener != null) {
                this.resultListener.onGalleryItemsCompleteCallback(photoItems);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }

}
