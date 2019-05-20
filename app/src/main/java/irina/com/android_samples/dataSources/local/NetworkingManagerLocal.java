package irina.com.android_samples.dataSources.local;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import irina.com.android_samples.dataSources.giphy.PhotoItemGiphy;
import irina.com.android_samples.dataSources.unsplash.PhotoItemUnsplash;
import irina.com.android_samples.interfaces.NetworkingManager;
import irina.com.android_samples.interfaces.NetworkingManagerResult;
import irina.com.android_samples.interfaces.PhotoItem;

public class NetworkingManagerLocal implements NetworkingManager {

    @Override
    public void getImages(NetworkingManagerResult result) {
        result.onGetItemsCompleteCallback(getAllSavedPhotoItems());
    }

    @Override
    public void fetchNewItemsFromPosition(int lastPosition, NetworkingManagerResult result) {

    }

    private List<PhotoItem> getAllSavedPhotoItems() {
        List<PhotoItem> photoItems = new ArrayList<>();
        photoItems.addAll(SugarRecord.listAll(PhotoItemGiphy.class));
        photoItems.addAll(SugarRecord.listAll(PhotoItemUnsplash.class));
        return photoItems;
    }
}
