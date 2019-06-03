package irina.com.android_samples.presenters.recyclerViewPresenters;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenter;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;

public class PhotoItemsPresenterRecyclerView implements PhotoItemsPresenter {

    List<PhotoItem> photoItems;
    Adapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void showPhotoItems(Activity activity, List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback, LinearLayout layout) {
        this.photoItems = photoItems;
        this.mRecyclerView = new RecyclerView(activity);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        this.mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity,2);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        this.mAdapter = new Adapter(photoItems, callback);
        this.mRecyclerView.setAdapter(mAdapter);

        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = ((GridLayoutManager)mLayoutManager).findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    callback.onLastItemReach(totalItemCount);
                }
            }
        });

        layout.removeAllViews();
        layout.addView(this.mRecyclerView);
        //activity.setContentView(mRecyclerView);
    }

    @Override
    public void updateWithItems(List<PhotoItem> photoItems) {
        this.mAdapter.photoItems = photoItems;
        this.mAdapter.notifyDataSetChanged();
    }

}
