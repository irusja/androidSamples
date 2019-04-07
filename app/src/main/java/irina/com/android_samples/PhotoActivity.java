package irina.com.android_samples;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);


        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();

        List<PhotoItem> photoItems = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        final Gson gson = new Gson();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String jsonString = response.body().string();
                try {
                    JSONArray array = new JSONArray(jsonString);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);
                        PhotoItem item = gson.fromJson(imgObject.toString(), PhotoItem.class);
                        photoItems.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(()-> {
                    showPhotoItems(photoItems);
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                int a = 0;
            }
        });

    }

    private void showPhotoItems(List<PhotoItem> items) {
        ArrayAdapter<PhotoItem> adapter = new ArrayAdapter<PhotoItem>(this, 0, items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                PhotoItem photoItem = items.get(position);

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.car_item, null, false);
                }

                ViewHolderCar viewHolderCar = (ViewHolderCar) convertView.getTag();
                if (viewHolderCar == null) {
                    viewHolderCar = new ViewHolderCar(convertView);
                    convertView.setTag(viewHolderCar);
                }

                viewHolderCar.textViewCarDescription.setText(photoItem.getName());
                Picasso.get().load(photoItem.getImgUrl()).into(viewHolderCar.imageViewCarPicture);

                return convertView;
            }
        };

        GridView view = findViewById(R.id.gridViewCars);
        view.setAdapter(adapter);
        view.setNumColumns(2);

    }

}
