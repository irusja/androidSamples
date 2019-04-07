package irina.com.android_samples.download;

import java.util.List;

import irina.com.android_samples.PhotoItem;

public interface NetworkResultListener {

    void onGalleryItemsCompleteCallback(List<PhotoItem> photoItems);

}
