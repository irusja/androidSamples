package irina.com.android_samples.dataSources.giphy;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

public class NetworkingManagerGiphy implements NetworkingManager {

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
        if (requestInProgress) {
            return;
        }
        if (offset <= lastPosition) {
            requestInProgress = true;
            offset += limit;
            getImages(result);
        }
    }

    private void getItems(NetworkingManagerResult result) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit=" + limit + "&offset=" + offset + "&rating=G")
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
                String jsonString = response.body() != null ? response.body().string() : null;
                try {
                    JsonElement jelement = new JsonParser().parse(jsonString);
                    JsonObject jsonData = jelement.getAsJsonObject();
                    JsonArray jsonArray = jsonData.getAsJsonArray("data");

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonElement imgObject = jsonArray.get(i);
                        PhotoItemGiphy item = gson.fromJson(imgObject.toString(), PhotoItemGiphy.class);
                        photoItems.add(item);
                    }
                } catch (Exception e) {
                    Log.e("ERROR", e.getLocalizedMessage());
                } finally {
                    requestInProgress = false;
                    result.onGetItemsCompleteCallback(photoItems);
                }
            }

        });
    }

}
