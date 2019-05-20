package irina.com.android_samples.interfaces;

import android.app.Activity;

import java.util.List;

public interface PhotoItemsPresenter {

    void showPhotoItems(Activity activity, List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback);
    void updateWithItems(List<PhotoItem> photoItems);

}
