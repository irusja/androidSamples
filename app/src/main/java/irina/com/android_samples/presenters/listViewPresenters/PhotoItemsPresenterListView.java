package irina.com.android_samples.presenters.listViewPresenters;

import android.app.Activity;
import android.widget.AbsListView;
import android.widget.LinearLayout;
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

    List<PhotoItem> photoItems;
    ViewAdapter adapter;

    @Override
    public void showPhotoItems(Activity activity, List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback, LinearLayout layout) {
        this.photoItems = photoItems;
        ListView view = new ListView(activity);
        this.adapter = new ViewAdapter(activity, R.layout.grid_view_item, this.photoItems, callback);
        view.setAdapter(adapter);

        view.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                    callback.onLastItemReach(totalItemCount);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){

            }
        });

        layout.removeAllViews();
        layout.addView(view);
        //activity.setContentView(view);
    }

    @Override
    public void updateWithItems(List<PhotoItem> photoItems) {
        this.photoItems = photoItems;
        this.adapter.notifyDataSetChanged();
    }

}
