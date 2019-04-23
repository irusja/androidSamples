package irina.com.android_samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import irina.com.android_samples.interfaces.PhotoItem;

public class BaseActivity extends Activity {

    private static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";
    private static String TEXT_VIEW_DATA_KEY = "TEXT_VIEW_DATA_KEY";

    protected PhotoItem photoItem;

    public static Intent buildIntent(Context context, PhotoItem photoItem) {
        Intent intent = new Intent(context, ShareActivityWithFragment.class); //should be ShareActivity
        intent.putExtra(PHOTO_ITEM_KEY, photoItem);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.photoItem = (PhotoItem) getIntent().getSerializableExtra(PHOTO_ITEM_KEY);
    }
}
