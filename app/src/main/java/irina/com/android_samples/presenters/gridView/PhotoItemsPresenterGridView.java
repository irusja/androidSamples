package irina.com.android_samples.presenters.gridView;

import android.app.Activity;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.gallery.ViewAdapter;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {

    @Override
    public void showPhotoItems(Activity activity, List<PhotoItem> photoItems) {
        GridView view = new GridView(activity);
        ViewAdapter adapter = new ViewAdapter(activity, R.layout.grid_view_item, photoItems);
        view.setAdapter(adapter);
        view.setNumColumns(2);
        setOnTouchListener(activity, view);

        activity.setContentView(view);
    }

    private void setOnTouchListener(Activity activity, GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            PhotoItem photoItem = (PhotoItem) adapterView.getItemAtPosition(position);

            Toast toast = Toast.makeText(activity.getApplicationContext(),
                    StringUtils.isNotBlank(photoItem.getLocation()) ? photoItem.getLocation() : "Unknown location",
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}
