package irina.com.android_samples.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.ShareActivity;
import irina.com.android_samples.dataSources.giph.NetworkingManagerGiphy;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;
import irina.com.android_samples.presenters.listViewPresenters.PhotoItemsPresenterListView;


public class GalleryListActivity extends AppCompatActivity implements NetworkingManagerResult, PhotoItemsPresenterCallback {

    private PhotoItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gallery);

        presenter = new PhotoItemsPresenterListView();

        NetworkingManager networkingManager = new NetworkingManagerGiphy(this);
        networkingManager.getImages();
    }

    @Override
    public void onGetItemsCompleteCallback(List<PhotoItem> photoItem) {
        runOnUiThread(() -> presenter.showPhotoItems(this, photoItem, this));
    }

    @Override
    public void onItemSelected(PhotoItem item) {
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }
}