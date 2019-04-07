package irina.com.android_samples.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import irina.com.android_samples.PhotoItem;
import irina.com.android_samples.R;
import irina.com.android_samples.download.NetworkManager;
import irina.com.android_samples.download.NetworkResultListener;
import irina.com.android_samples.download.impl.NetworkManagerImpl;

public class GalleryActivity extends AppCompatActivity implements NetworkResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        NetworkManager networkManager = new NetworkManagerImpl(this);
        networkManager.getGalleryItems();
    }

    @Override
    public void onGalleryItemsCompleteCallback(List<PhotoItem> photoItems) {
        runOnUiThread(() -> showPhotoItems(photoItems));
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
