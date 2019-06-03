package irina.com.android_samples.interfaces;

import android.app.Activity;
import android.widget.LinearLayout;

import java.util.List;

public interface PhotoItemsPresenter {

    void showPhotoItems(Activity activity, List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback, LinearLayout layout);
    void updateWithItems(List<PhotoItem> photoItems);

}
