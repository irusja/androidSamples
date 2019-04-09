package irina.com.android_samples.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.dataSources.giph.NetworkingManagerGiphy;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.presenters.listV.PhotoItemsPresenterListView;


public class GalleryActivity extends AppCompatActivity implements NetworkingManagerResult {

    private PhotoItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //presenter = new PhotoItemsPresenterGridView();
        presenter = new PhotoItemsPresenterListView();

        //NetworkingManager networkingManager = new NetworkingManagerUnsplash(this);
        NetworkingManager networkingManager = new NetworkingManagerGiphy(this);
        networkingManager.getImages();
    }

    @Override
    public void onGetItemsCompleteCallback(List<PhotoItem> photoItem) {
        runOnUiThread(() -> presenter.showPhotoItems(this, photoItem));
    }

}
