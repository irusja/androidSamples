package irina.com.android_samples.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import irina.com.android_samples.R;
import irina.com.android_samples.ShareActivityWithFragment;
import irina.com.android_samples.dataSources.giphy.NetworkingManagerGiphy;
import irina.com.android_samples.dataSources.local.NetworkingManagerLocal;
import irina.com.android_samples.dataSources.unsplash.NetworkingManagerUnsplash;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;
import irina.com.android_samples.presenters.gridViewPresenters.PhotoItemsPresenterGridView;
import irina.com.android_samples.presenters.listViewPresenters.PhotoItemsPresenterListView;
import irina.com.android_samples.presenters.recyclerViewPresenters.PhotoItemsPresenterRecyclerView;

import static irina.com.android_samples.gallery.GalleryActivity.ImageProvider.Favorites;
import static irina.com.android_samples.gallery.GalleryActivity.ImageProvider.Giphy;
import static irina.com.android_samples.gallery.GalleryActivity.ImageProvider.Unsplash;

public class GalleryActivity extends AppCompatActivity implements PhotoItemsPresenterCallback {

    //TODO: Change URL for Unsplash
    //*TODO 6 EXTRA EXTRA: Implement safe remove (do not delete anything from database)

    private static String IMAGE_PROVIDER_KEY = "IMAGE_PROVIDER_KEY";
    private static String PRESENTER_KEY = "PRESENTER_KEY";

    public enum ImageProvider {
        Unsplash,
        Giphy,
        Favorites
    }

    public enum PresenterType {
        Grid,
        List,
        Recycler
    }

    private NetworkingManager networkingManager = null;
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
            case Recycler:
                presenter = new PhotoItemsPresenterRecyclerView();
                break;
        }

        showImagesService(imageProvider);
    }

    private void showImagesService(ImageProvider imageProvider) {
        // Set networking
        switch (imageProvider) {
            case Unsplash:
                networkingManager = new NetworkingManagerUnsplash();
                register_event(Unsplash.name());
                break;
            case Giphy:
                networkingManager = new NetworkingManagerGiphy();
                register_event(Giphy.name());
                break;
            case Favorites:
                networkingManager = new NetworkingManagerLocal();
                register_event(Favorites.name());
                break;
        }

        // Get images
        getImages();
    }

    private void register_event(String serviceName) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, serviceName);
        FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void onItemSelected(PhotoItem item) {
        Intent intent = ShareActivityWithFragment.buildIntent(this, item, ShareActivityWithFragment.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);

        final MenuItem favoriteMenuItem = menu.findItem(R.id.action_show_favotites);
        favoriteMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        favoriteMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImagesService(ImageProvider.Favorites);
            return true;
        });

        final MenuItem showUnsplashMenuItem = menu.findItem(R.id.action_show_unslash);
        showUnsplashMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        showUnsplashMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImagesService(ImageProvider.Unsplash);
            return true;
        });

        final MenuItem showGiphyMenuItem = menu.findItem(R.id.action_show_giphy);
        showGiphyMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        showGiphyMenuItem.setOnMenuItemClickListener(menuItem -> {
            showImagesService(ImageProvider.Giphy);
            return true;
        });

        return true;
    }

    @Override
    public void onItemToggleFavorite(PhotoItem item) {
        if (item.isSavedToDatabase()) {
            item.deleteFromDatabase();
            getImages();
        } else {
            item.saveToDatabase();
        }
    }

    private void getImages() {
        networkingManager.getImages(photoItems ->
                runOnUiThread(()-> {
                    presenter.showPhotoItems(this, photoItems, this);
                })
        );
    }

    @Override
    public void onLastItemReach(int position) {
        networkingManager.fetchNewItemsFromPosition(position, photoItems -> {
            runOnUiThread(()-> {
                presenter.updateWithItems(photoItems);
            });
        });
    }

}
