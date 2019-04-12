package irina.com.android_samples.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.dataSources.unsplash.NetworkingManagerUnsplash;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.presenters.gridView.PhotoItemsPresenterGridView;


public class GalleryGridActivity extends AppCompatActivity implements NetworkingManagerResult {

    private PhotoItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_gallery);

        presenter = new PhotoItemsPresenterGridView();

        NetworkingManager networkingManager = new NetworkingManagerUnsplash(this);
        networkingManager.getImages();
    }

    @Override
    public void onGetItemsCompleteCallback(List<PhotoItem> photoItem) {
        runOnUiThread(() -> presenter.showPhotoItems(this, photoItem));
    }

}
