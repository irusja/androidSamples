package irina.com.android_samples.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.dataSources.giph.NetworkingManagerGiphy;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;

public class GalleryActivity extends AppCompatActivity implements NetworkingManagerResult {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //NetworkingManager networkingManager = new NetworkingManagerUnsplash(this);
        NetworkingManager networkingManager = new NetworkingManagerGiphy(this);
        networkingManager.getImages();
    }

    @Override
    public void onGetItemsCompleteCallback(List<PhotoItem> photoItem) {
        runOnUiThread(() -> showPhotoItems(photoItem));
    }

    private void showPhotoItems(List<PhotoItem> items) {
        GridView view = findViewById(R.id.gridViewGallery);
        GridViewAdapter adapter = new GridViewAdapter(this, R.layout.grid_view_item, items);
        view.setAdapter(adapter);
        view.setNumColumns(2);
        setOnTouchListener(view);
    }

    private void setOnTouchListener(GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            PhotoItem photoItem = (PhotoItem) adapterView.getItemAtPosition(position);

            Toast toast = Toast.makeText(getApplicationContext(), StringUtils.isNotBlank(photoItem.getLocation()) ? photoItem.getLocation() : "Unknown location", Toast.LENGTH_SHORT);
            toast.show();
        });
    }

}
