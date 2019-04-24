package irina.com.android_samples.presenters.listViewPresenters;

import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.gallery.ViewAdapter;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;

public class PhotoItemsPresenterListView implements PhotoItemsPresenter {

    @Override
    public void showPhotoItems(Activity activity, List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback) {
        ListView view = new ListView(activity);
        ViewAdapter adapter = new ViewAdapter(activity, R.layout.grid_view_item, photoItems);
        view.setAdapter(adapter);
        //setOnTouchListener(activity, view);
        view.setOnItemClickListener((adapterView, gridView, position, id) -> {
            callback.onItemSelected(photoItems.get(position));
        });

        activity.setContentView(view);
    }

    private void setOnTouchListener(Activity activity, ListView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            PhotoItem photoItem = (PhotoItem) adapterView.getItemAtPosition(position);

            Toast toast = Toast.makeText(activity.getApplicationContext(),
                    StringUtils.isNotBlank(photoItem.getLocation()) ? photoItem.getLocation() : "Unknown location",
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}
