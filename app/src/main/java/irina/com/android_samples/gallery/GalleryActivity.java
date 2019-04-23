package irina.com.android_samples.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.ShareActivityWithFragment;
import irina.com.android_samples.dataSources.giph.NetworkingManagerGiphy;
import irina.com.android_samples.dataSources.unsplash.NetworkingManagerUnsplash;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;
import irina.com.android_samples.presenters.gridView.PhotoItemsPresenterGridView;
import irina.com.android_samples.presenters.listV.PhotoItemsPresenterListView;

public class GalleryActivity extends AppCompatActivity implements NetworkingManagerResult, PhotoItemsPresenterCallback {

    private static String IMAGE_PROVIDER_KEY = "IMAGE_PROVIDER_KEY";
    private static String PRESENTER_KEY = "PRESENTER_KEY";

    public enum ImageProvider {
        Unsplash,
        Giphy
    }

    public enum PresenterType {
        Grid,
        List
    }

    private PhotoItemsPresenter presenter;

    public static Intent buildIntent(Context context, ImageProvider imageProvider, PresenterType presenterType) {
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(IMAGE_PROVIDER_KEY, imageProvider);
        intent.putExtra(PRESENTER_KEY, presenterType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_gallery);

        // Get param variables
        ImageProvider imageProvider = (ImageProvider) getIntent().getExtras().get(IMAGE_PROVIDER_KEY);
        PresenterType presenterType = (PresenterType) getIntent().getExtras().get(PRESENTER_KEY);

        // If no param variables -> exit
        if (imageProvider == null || presenterType == null) {
            return;
        }

        // Set presenter
        switch (presenterType) {
            case Grid:
                presenter = new PhotoItemsPresenterGridView();
                break;
            case List:
                presenter = new PhotoItemsPresenterListView();
                break;
        }

        // Set networking
        NetworkingManager networkingManager = null;
        switch (imageProvider) {
            case Unsplash:
                networkingManager = new NetworkingManagerUnsplash(this);
                break;
            case Giphy:
                networkingManager = new NetworkingManagerGiphy(this);
                break;
        }

        // Get images
        networkingManager.getImages();
    }

    @Override
    public void onGetItemsCompleteCallback(List<PhotoItem> photoItem) {
        runOnUiThread(() -> presenter.showPhotoItems(this, photoItem, this));
    }

    @Override
    public void onItemSelected(PhotoItem item) {
//        Intent intent = ShareActivity.buildIntent(this, item);
//        startActivity(intent);

        //Intent intent = new Intent(this, ShareActivityWithFragment.class);
        //startActivity(intent);

        Intent intent = ShareActivityWithFragment.buildIntent(this, item);
        startActivity(intent);
    }

}
